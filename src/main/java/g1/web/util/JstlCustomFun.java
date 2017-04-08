package g1.web.util;



import java.util.*;

import cmn2.util.*;

/*
目前由于 jstl(jsp)放弃使用了，这个文件用不着了。
 */
public class JstlCustomFun {
	static public Date convertDateFromIntSeconds(int secondsFromBegin){
		return Util1.convertDateFromIntSeconds(secondsFromBegin);
	}
}
