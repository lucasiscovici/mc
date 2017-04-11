package Web;
import javax.servlet.http.HttpServletRequest;

public class Base {
	public static String  base2 = "/";

	public static String  base = base2+"TwisterV3/";

	public static String  baseCSS = base+"css/";
	public static String  baseVue = base.concat("vue/");
	public static String  baseTemplates = base.concat("templates/");
	public static String  baseVue2 = base2.concat("vue/");
	public static String  baseTemplates2 = base2.concat("templates/");
	public static String  baseCSSLib = baseCSS.concat("lib/");
	public static String  baseJS = base.concat("js/");
	public static String baseJSLib = baseJS.concat("lib/");
	public static String baseJSConfigLib = baseJS.concat("config_lib/");
	public static String baseJSClasses = baseJS.concat("classes/");
	
	public static void fromRequest(HttpServletRequest request) {
		request.setAttribute("base", base);
		request.setAttribute("baseCSS", baseCSS);
		request.setAttribute("baseCSSLib", baseCSSLib);
		request.setAttribute("baseJS", baseJS);
		request.setAttribute("baseJSLib", baseJSLib);
		request.setAttribute("baseJSConfigLib", baseJSConfigLib);
		request.setAttribute("baseJSClasses", baseJSClasses);
		request.setAttribute("baseVue", baseVue);
		request.setAttribute("baseTemplates", baseTemplates);
		request.setAttribute("baseVue2", baseVue2);
		request.setAttribute("baseTemplates2", baseTemplates2);
	}
	
}
