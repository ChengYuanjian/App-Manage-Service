package com.cyj.main.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.cyj.bo.User;
import com.cyj.util.JSONUtil;

public class TestMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//[{湖北：{武汉：{洪山区,青山区},襄阳：{樊城区,襄城区}}}]
		
		Map<String,Map<String,List<String>>> area = new HashMap<String,Map<String,List<String>>>();
		
		List<String> districts1 = new ArrayList<String>();
		districts1.add("洪山区");
		districts1.add("青山区");
		districts1.add("汉江区");
		
		Map<String,List<String>> city= new HashMap<String,List<String>>();
		city.put("武汉", districts1);
		
		List<String> districts2 = new ArrayList<String>();
		districts2.add("襄城区");
		districts2.add("樊城区");
		districts2.add("襄洲区");
		
		city.put("襄阳", districts2);
		
		
		List<String> districts11 = new ArrayList<String>();
		districts11.add("西湖区");
		districts11.add("上城区");
		districts11.add("下城区");
		
		Map<String,List<String>> city1= new HashMap<String,List<String>>();
		city1.put("杭州", districts11);
		
		List<String> districts12 = new ArrayList<String>();
		districts12.add("A区");
		districts12.add("B区");
		districts12.add("C区");
		
		city1.put("宁波", districts12);
		
		
		area.put("湖北", city);
		area.put("浙江", city1);
		
		System.out.println(area);
		System.out.println(JSONObject.fromObject(area).toString());
		
		System.out.println(JSONObject.fromObject(new User()));
		
		String xx = "{'湖北':{'襄阳':['襄城区','樊城区','襄洲区'],'武汉':['洪山区','青山区','汉江区']},'浙江':{'宁波':['A区','B区','C区'],'杭州':['西湖区','上城区','下城区']}}";
		
		JSONObject jj =JSONObject.fromObject(xx);

		Map<String,Map<String,List<String>>> yy = (HashMap<String,Map<String,List<String>>>) JSONObject.toBean(jj, HashMap.class);
		

		StringBuffer sb = new StringBuffer();
		
		String path = TestMain.class.getResource("/").getPath();
		System.out.println(path);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path+"\\json\\areas.json"),"UTF-8"));  
		String line = null;  
		while ((line = br.readLine()) != null) {  
			sb.append(line);
		}
		
	
	
		 
		
		String js = sb.toString();
//			"{\"regtime\":\"2014-12-12\",\"pwd\":\"\",\"phone\":\"\",\"nickname\":\"\",\"userid\":0,\"iconurl\":\"\",\"a\":1}";
		
		System.out.println(js.charAt(1));

		if(!js.startsWith("{"))
		{
			js = js.substring(js.indexOf("{"));
		}
		
		

		Map<String,Object> areamap = JSONUtil.parseJSON2Map(js);
		
		for(String s1:areamap.keySet())
		{
			Map<String,List<String>> m1 = (Map<String,List<String>>)areamap.get(s1);
			System.out.println(s1);
			for(String s2:m1.keySet())
			{
				List<String> l3 = m1.get(s2);
				System.out.println("\t"+s2);
				for(String s3:l3)
					System.out.println("\t\t"+s3);
			}
		}
		
		
		Date date = new Date();
		
		System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
		
		String usr = "{\"userid\":1,\"nickname\":\"萌小美\",\"phone\":\"123456\"}";
		//String usr = "{'userid':1,'nickname':'萌小美','phone':'123456','regtime':'2014-12-12','iconurl':'/image/xx.png'}";
		User user = (User)JSONObject.toBean(JSONObject.fromObject(usr),User.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);

		User uuu = objectMapper.readValue(usr, User.class);
		System.out.println();
	}

}
