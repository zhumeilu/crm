/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2016-04-19 13:59:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>小码哥客户关系管理系统</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/default/easyui.css\">   \r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/js/jquery-easyui/themes/icon.css\">   \r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.min.js\"></script>   \r\n");
      out.write("<script type=\"text/javascript\" src=\"/js/jquery-easyui/jquery.easyui.min.js\"></script>  ");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\t$(document).on(\"keyup\",function(obj){\r\n");
      out.write("\t\tif(obj.keyCode==13){\r\n");
      out.write("\t\t\tsubmitForm();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("})\r\n");
      out.write("\tfunction submitForm(){\r\n");
      out.write("\t\t$(\"#myForm\").form(\"submit\",{    \r\n");
      out.write("\t\t    url:\"/login.do\",    \r\n");
      out.write("\t\t    success:function(data){\r\n");
      out.write("\t\t    \tvar json=$.parseJSON(data);\r\n");
      out.write("\t\t    \tif(json.success){\r\n");
      out.write("\t\t    \t\twindow.location.href=\"/index.do\";\r\n");
      out.write("\t\t    \t}else{\r\n");
      out.write("\t\t    \t\t$.messager.alert(\"温馨提示\",json.msg,\"info\")\r\n");
      out.write("\t\t    \t} \r\n");
      out.write("\t\t    }    \r\n");
      out.write("\t\t});  \r\n");
      out.write("\t\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
session.invalidate(); 
      out.write("\r\n");
      out.write("  <section class=\"container\">\r\n");
      out.write("    <div class=\"login\">\r\n");
      out.write("      <h1>用户登录</h1>\r\n");
      out.write("      <form id=\"myForm\" method=\"post\">\r\n");
      out.write("        <p><input type=\"text\" name=\"username\" value=\"admin\" placeholder=\"账号\"></p>\r\n");
      out.write("        <p><input type=\"password\" name=\"password\" value=\"123\" placeholder=\"密码\"></p>\r\n");
      out.write("        <p class=\"submit\">\r\n");
      out.write("        \t<input type=\"button\" value=\"登录\" onclick=\"submitForm()\">\r\n");
      out.write("        \t<input type=\"button\" value=\"重置\" onclick=\"resetForm()\">\r\n");
      out.write("        </p>\r\n");
      out.write("      </form>\r\n");
      out.write("    </div>\r\n");
      out.write("  </section>\r\n");
      out.write("<div style=\"text-align:center;\" class=\"login-help\">\r\n");
      out.write("\t<p>Copyright ©2015 广州小码哥教育科技有限公司</p>\r\n");
      out.write("</div>\r\n");
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
