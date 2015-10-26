/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.util.comm;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.util.comm.ExportTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-24 10:57
 *   LastChange: 2015-10-24 10:57
 *      History:
 * </pre>
 *********************************************************************************************/
public class ExportTest
{
	public static void main( String[] args )
	{
		try
		{
			// 输出流
			OutputStream os = new FileOutputStream( "D:\\workspace_my3\\pro-common\\export2007_" + System.currentTimeMillis() + ".xlsx" );
			// 工作区
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet( "test" );
			for ( int i = 0 ; i < 1000 ; i++ )
			{
				// 创建第一个sheet
				// 生成第一行
				XSSFRow row = sheet.createRow( i );
				// 给这一行的第一列赋值
				row.createCell( 0 ).setCellValue( "column1" );
				// 给这一行的第一列赋值
				row.createCell( 1 ).setCellValue( "column2" );
				System.out.println( i );
			}
			// 写文件
			wb.write( os );
			// 关闭输出流
			os.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
}
