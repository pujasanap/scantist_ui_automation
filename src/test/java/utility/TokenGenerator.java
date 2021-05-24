package utility;
		
	import org.testng.annotations.Test;
	import org.testng.annotations.BeforeTest;
	import static org.hamcrest.Matchers.equalTo;

	import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	import static io.restassured.RestAssured.*;
	import java.io.FileNotFoundException;
	import java.util.HashMap;
	import java.util.Map;

	public class TokenGenerator{
		public static Map<String, String> map = new HashMap<String, String>();
		public static String token;

		@Test
		public void testLogin(){
			map.put("email","scantist3@gmail.com");
	        map.put("password","e3bccf84b8b0306366d6922d0dc9a0cbf3b58fd89b2698c509be1f75ea0b54c6");
			RestAssured.baseURI="https://api.scantist.io";
			RestAssured.basePath="/v1/rest-auth/login/";
			Response response = RestAssured.given()
			.contentType("application/json")
			.body(map)
			.when()
			.post()
			.then()
			.statusCode(200)
			.and()
	        .extract().response();
			token = response.getBody().jsonPath().getString("token"); 
						
			System.out.println("Login Token =====> "+token);	
			
			Response response1 = RestAssured.given()
					.headers("authorization","Token "+token)
					.contentType("application/json")
					.when()
					.delete("/orgs/539/projects/4072/")
					.then()
					.statusCode(204)
					.and()
					.extract().response();		
			      
					System.out.println("Response body of remove Team  ==> " + response1.body().asString()); 
	   }
		
	}
