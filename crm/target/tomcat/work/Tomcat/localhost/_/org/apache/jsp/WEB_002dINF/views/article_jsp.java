/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2016-04-23 10:15:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class article_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/common/common.jsp", Long.valueOf(1460719054787L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>文章管理</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/default/easyui.css\">   \r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/icon.css\">   \r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.min.js\"></script>   \r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.easyui.min.js\"></script>  ");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/view/article.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- 数据表格 -->\r\n");
      out.write("\t<table id=\"article_datagrid\"></table>\r\n");
      out.write("\t<!-- CRUD按钮 -->\r\n");
      out.write("\t<div id=\"article_bt\">\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-add\" plain=\"true\" data-cmd=\"add\">新增</a> \r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-edit\"  plain=\"true\" data-cmd=\"edit\">编辑</a>\r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-remove\" plain=\"true\" data-cmd=\"del\">删除</a> \r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-reload\" plain=\"true\" data-cmd=\"refresh\">刷新</a>\r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-reload\" plain=\"true\" data-cmd=\"updateIndex\">更新索引</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t关键字:<input type=\"text\" id=\"keyContent\">\r\n");
      out.write("\t\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-search\" data-cmd=\"search\">查询</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 编辑框 -->\r\n");
      out.write("\t<div id=\"article_dialog\">\r\n");
      out.write("\t\t<form id=\"article_dialog_form\" action=\"save.json\" method=\"post\">\r\n");
      out.write("\t\t\t<table align=\"center\" style=\"margin-top: 15px;\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"id\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>标题</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"title\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>内容</td>\r\n");
      out.write("\t\t\t\t\t<td><textarea rows=\"\" cols=\"\" name=\"content\"></textarea></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 底部按钮 -->\r\n");
      out.write("\t<div id=\"article_dialog_bb\">\r\n");
      out.write("\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-save\" plain=\"true\" data-cmd=\"save\">保存</a> \r\n");
      out.write("\t\t<a class=\"easyui-linkbutton\" iconCls=\"icon-cancel\"  plain=\"true\" data-cmd=\"cancel\">取消</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- 文章显示 -->\r\n");
      out.write("\t<div id=\"article_show\"></div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
