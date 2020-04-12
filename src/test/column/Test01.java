package test.column;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.Top;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;


/** 
 * @ClassName: JsqlparserDemo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yuechang yuechang5@hotmail.com 
 * @date 2015年9月2日 上午11:40:59 
 */
public class Test01 {


    public static void main(String[] args) throws JSQLParserException {
//        demo();
//        System.out.println("====================================");
//        demo2();
//        System.out.println("====================================");
//        demo3();
        System.out.println("====================================");
        demo4();
    }

    public static void demo4() {
    	CCJSqlParserManager pm = new CCJSqlParserManager();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select aa,bb,cc,dd from ");
        stringBuffer.append("A ,W left join B on a.bid = B.id ");
        stringBuffer.append("       left join C on A.cid = C.id ");
        stringBuffer.append("       left join D on B.did = D.id ");
        stringBuffer.append("where C1 = 1 and C2 = ? and C3='张三' ");
        //stringBuffer.append("where C1 = 1 and C2 = ? and C3='张三' and C4 = 678 and C5 = '456' ");
        String sql=stringBuffer.toString();
        
        try {
			Statement statement = pm.parse(new StringReader(stringBuffer.toString()));
			
			if (statement instanceof Select) {
	            //获得Select对象
	        	Select select = (Select)CCJSqlParserUtil.parse(sql);
	            SelectBody selectBody = select.getSelectBody();
	            PlainSelect plainSelect = (PlainSelect)selectBody;
	            //获得where条件表达式
	            Expression where = plainSelect.getWhere();
	            //获取select字段
	            List<SelectItem> columns=plainSelect.getSelectItems();
	            for (SelectItem selectItem : columns) {
	            	System.out.println(selectItem);
				}
	            //初始化接收获得到的字段信息
	            StringBuffer allColumnNames = new StringBuffer();
	            //BinaryExpression包括了整个where条件，
	            //例如：AndExpression/LikeExpression/OldOracleJoinBinaryExpression
	            if(where instanceof BinaryExpression){
	            	
	                Expression leftExpression = ((BinaryExpression) where).getLeftExpression();
	                Expression rightExpression = ((BinaryExpression) where).getRightExpression();
	            	String stringExpression = ((BinaryExpression) where).getStringExpression();
	            	System.out.println(where);
	                System.out.println(leftExpression);
	                System.out.println(rightExpression);
	            	System.out.println(stringExpression);
	                
	                allColumnNames = getColumnName((BinaryExpression)(where),allColumnNames);
	                System.out.println(allColumnNames.toString());
	            }
	        }
			
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
    }


    /**
     * 获得where条件字段中列名，以及对应的操作符
     * @Title: getColumnName 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param expression 
     * @param @param allColumnNames
     * @param @return 设定文件 
     * @return StringBuffer 返回类型 
     * @throws
     */
    private static StringBuffer getColumnName(Expression expression,StringBuffer allColumnNames) {

        String columnName = null;
        if(expression instanceof BinaryExpression){
            //获得左边表达式
            Expression leftExpression = ((BinaryExpression) expression).getLeftExpression();
            //如果左边表达式为Column对象，则直接获得列名
            if(leftExpression  instanceof Column){
                //获得列名
                columnName = ((Column) leftExpression).getColumnName();
                allColumnNames.append(columnName);
                allColumnNames.append(":");
                //拼接操作符
                allColumnNames.append(((BinaryExpression) expression).getStringExpression());
                //allColumnNames.append("-");
            }
            //否则，进行迭代
            else if(leftExpression instanceof BinaryExpression){
                getColumnName((BinaryExpression)leftExpression,allColumnNames);
            }

            //获得右边表达式，并分解
            Expression rightExpression = ((BinaryExpression) expression).getRightExpression();
            if(rightExpression instanceof BinaryExpression){
                Expression leftExpression2 = ((BinaryExpression) rightExpression).getLeftExpression();
                if(leftExpression2 instanceof Column){
                    //获得列名
                    columnName = ((Column) leftExpression2).getColumnName();
                    allColumnNames.append("|");
                    allColumnNames.append(columnName);
                    allColumnNames.append(":");
                    //获得操作符
                    allColumnNames.append(((BinaryExpression) rightExpression).getStringExpression());
                    allColumnNames.append(((BinaryExpression) rightExpression).getRightExpression());
                }
            }
        }
        return allColumnNames;
    }
    
    

    public static void demo() throws JSQLParserException{
        CCJSqlParserManager pm = new CCJSqlParserManager();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("update ac_operator op ");
        stringBuffer.append("set op.errcount=(");
        stringBuffer.append("(select case when op1.errcount is null then 0 else op1.errcount end as errcount ");
        stringBuffer.append("from ac_operator op1 ");
        stringBuffer.append("where op1.loginname = '中国' )+1");
        stringBuffer.append("),lastlogin='中国' ");
        stringBuffer.append("where 1=1 and columnName2 = ? and columnName3 = '678' and columnName4 = '456'");
        
        

        Statement statement = pm.parse(new StringReader(stringBuffer.toString()));

        if (statement instanceof Update) {
            //获得Update对象
            Update updateStatement = (Update) statement;
            //获得表名
            //System.out.println("table:"+updateStatement.getTable().getName());
            //获得where条件表达式
            Expression where = updateStatement.getWhere();
            //初始化接收获得到的字段信息
            StringBuffer allColumnNames = new StringBuffer();
            //BinaryExpression包括了整个where条件，
            //例如：AndExpression/LikeExpression/OldOracleJoinBinaryExpression
            System.out.println(where);
            if(where instanceof BinaryExpression){
                allColumnNames = getColumnName((BinaryExpression)(where),allColumnNames);
                System.out.println("allColumnNames:"+allColumnNames.toString());
            }
        }
    }
    
    public static void demo2() throws JSQLParserException{
        CCJSqlParserManager pm = new CCJSqlParserManager();

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from ");
        stringBuffer.append("A as a left join B on a.bid = B.id ");
        stringBuffer.append("       left join C on A.cid = C.id ");
        stringBuffer.append("       left join D on B.did = D.id ");
        stringBuffer.append("where 1=1 and  columnName2 = ? and columnName3 = '678' and columnName4 = '456'");
        String sql=stringBuffer.toString();
        Statement statement = pm.parse(new StringReader(stringBuffer.toString()));

        if (statement instanceof Select) {
            //获得Select对象
        	Select select = (Select)CCJSqlParserUtil.parse(sql);
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect)selectBody;
            
            //获得where条件表达式
            Expression where = plainSelect.getWhere();
            //初始化接收获得到的字段信息
            StringBuffer allColumnNames = new StringBuffer();
            //BinaryExpression包括了整个where条件，
            //例如：AndExpression/LikeExpression/OldOracleJoinBinaryExpression
            System.out.println(where);
            if(where instanceof BinaryExpression){
                allColumnNames = getColumnName((BinaryExpression)(where),allColumnNames);
                System.out.println("allColumnNames:"+allColumnNames.toString());
            }
        }
    }
    
    public static void demo3() throws JSQLParserException{
		String sql = "select * from "
				   + "A as a left join B on a.bid = B.id "
				   + "       left join C on A.cid = C.id "
				   + "       left join D on B.did = D.id "
				   + "where a.id=? ";
        try {
            Select select = (Select)CCJSqlParserUtil.parse(sql);
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect)selectBody;
 
            Expression where = plainSelect.getWhere();
//            ExpressionDeParser expressionDeParser = new ExpressionDeParser();
//            plainSelect.getWhere().accept(expressionDeParser);
            
            // 此处根据where实际情况强转 最外层
            EqualsTo equalsTo = (EqualsTo)where;
            System.out.println("Table:"+((Column)equalsTo.getLeftExpression()).getTable());
            System.out.println("Field:"+((Column)equalsTo.getLeftExpression()).getColumnName());
            System.out.println("equal:"+equalsTo.getRightExpression());
 
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }

}
