package Web;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import util.Dico;
import util.Parameters;
import util.io;

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
	public static String baseImg = base.concat("img/");

	public static void fromRequest(HttpServletRequest request) throws InstantiationException, IllegalAccessException {
		request.setAttribute("base2", base2);
		request.setAttribute("baseImg", baseImg);
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
