package external;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monkeylearn.ExtraParam;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

public class MonkeyLearnClient {

	private static final String API_KEY = "e07145aee05578b3362b3ccdf1a4ba92424de0d6";

	public static List<List<String>> extractKeywords(String[] text) {
		// Use the API key from your account
		MonkeyLearn ml = new MonkeyLearn(API_KEY);

		// Use the keyword extractor
		ExtraParam[] extraParams = { new ExtraParam("max_keywords", "3") };
		MonkeyLearnResponse response;
		try {
			response = ml.extractors.extract("ex_YCya9nrn", text, extraParams);
			JSONArray resultArray = response.arrayResult;
			return getKeywords(resultArray);
		} catch (MonkeyLearnException e) {// it’s likely to have an exception
			e.printStackTrace();
		}
		return new ArrayList<>();

	}

	private static List<List<String>> getKeywords(JSONArray mlResultArray) {
		List<List<String>> topKeywords = new ArrayList<>();
		// Iterate the result array and convert it to our format.
		for (int i = 0; i < mlResultArray.size(); ++i) {
			List<String> keywords = new ArrayList<>();
			JSONArray keywordsArray = (JSONArray) mlResultArray.get(i);
			for (int j = 0; j < keywordsArray.size(); ++j) {
				JSONObject keywordObject = (JSONObject) keywordsArray.get(j);
				// We just need the keyword, excluding other fields.
				String keyword = (String) keywordObject.get("keyword");
				keywords.add(keyword);

			}
			topKeywords.add(keywords);
		}
		return topKeywords;
	}
	
	public static void main(String[] args) {
		String[] textList = {
				"What is 2019 novel coronavirus?\n" + 
				"The 2019 novel coronavirus (2019-nCoV) is a new virus that\n" + 
				"causes respiratory illness in people and can spread from\n" + 
				"person to person. This virus was first identified during an\n" + 
				"investigation into an outbreak in Wuhan, China.\n" + 
				"Can people in the U.S. get 2019-nCoV?\n" + 
				"The 2019-nCoV is spreading from person to person in China\n" + 
				"and limited spread among close contacts has been detected\n" + 
				"in some countries outside China, including the United\n" + 
				"States. At this time, however, this virus is NOT currently\n" + 
				"spreading in communities in the United States. Right now,\n" + 
				"the greatest risk of infection is for people in China or people\n" + 
				"who have traveled to China. Risk of infection is dependent\n" + 
				"on exposure. Close contacts of people who are infected are\n" + 
				"at greater risk of exposure, for example health care workers\n" + 
				"and close contacts of people who are infected with 2019-\n" + 
				"nCoV. CDC continues to closely monitor the situation.\n" + 
				"Have there been cases of 2019-nCoV in the U.S.?\n" + 
				"Yes. The first infection with 2019-nCoV in the United States\n" + 
				"was reported on January 21, 2020. The current count of\n" + 
				"cases of infection with 2019-nCoV in the United States\n" + 
				"is available on CDC’s webpage at https://www.cdc.gov/\n" + 
				"coronavirus/2019-ncov/cases-in-us.html.\n" + 
				"How does 2019-nCoV spread?\n" + 
				"This virus probably originally emerged from an animal\n" + 
				"source but now seems to be spreading from person to\n" + 
				"person. It’s important to note that person-to-person spread\n" + 
				"can happen on a continuum. Some viruses are highly\n" + 
				"contagious (like measles), while other viruses are less so. At\n" + 
				"this time, it’s unclear how easily or sustainably this virus is\n" + 
				"spreading between people. Learn what is known about the\n" + 
				"spread of newly emerged coronaviruses at https://www.cdc.\n" + 
				"gov/coronavirus/2019-ncov/about/transmission.html.\n" + 
				"What are the symptoms of 2019-nCoV?\n" + 
				"Patients with 2019-nCoV have reportedly had mild to severe\n" + 
				"respiratory illness with symptoms of\n" + 
				"• fever\n" + 
				"• cough\n" + 
				"• shortness of breath\n" + 
				"What are severe complications from this virus?\n" + 
				"Many patients have pneumonia in both lungs.\n" + 
				"How can I help protect myself?\n" + 
				"The best way to prevent infection is to avoid being exposed\n" + 
				"to this virus.\n" + 
				"There are simple everyday preventive actions to\n" + 
				"help prevent the spread of respiratory viruses.\n" + 
				"These include\n" + 
				"• Avoid close contact with people who are sick.\n" + 
				"• Avoid touching your eyes, nose, and mouth with\n" + 
				"unwashed hands.\n" + 
				"• Wash your hands often with soap and water for at least 20\n" + 
				"seconds. Use an alcohol-based hand sanitizer that contains\n" + 
				"at least 60% alcohol if soap and water are not available.\n" + 
				"If you are sick, to keep from spreading respiratory\n" + 
				"illness to others, you should\n" + 
				"• Stay home when you are sick.\n" + 
				"• Cover your cough or sneeze with a tissue, then throw the\n" + 
				"tissue in the trash.\n" + 
				"• Clean and disinfect frequently touched objects\n" + 
				"and surfaces.\n" + 
				"What should I do if I recently traveled to China\n" + 
				"and got sick?\n" + 
				"If you were in China within the past 14 days and feel sick\n" + 
				"with fever, cough, or difficulty breathing, you should seek\n" + 
				"medical care. Call the office of your health care provider\n" + 
				"before you go and tell them about your travel and your\n" + 
				"symptoms. They will give you instructions on how to get care\n" + 
				"without exposing other people to your illness. While sick,\n" + 
				"avoid contact with people, don’t go out and delay any travel\n" + 
				"to reduce the possibility of spreading illness to others.\n" + 
				"Is there a vaccine?\n" + 
				"There is currently no vaccine to protect against 2019-nCoV.\n" + 
				"The best way to prevent infection is to avoid being exposed\n" + 
				"to this virus.\n" + 
				"Is there a treatment?\n" + 
				"There is no specific antiviral treatment for 2019-nCoV.\n" + 
				"People with 2019-nCov can seek medical care to help\n" + 
				"relieve symptoms.", };
	
		List<List<String>> words = extractKeywords(textList);
		for (List<String> ws : words) {
			for (String w : ws) {
				System.out.println(w);
			}
			System.out.println();
		}

	}

}
