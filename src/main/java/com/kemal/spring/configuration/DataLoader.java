package com.kemal.spring.configuration;

import com.kemal.spring.domain.Role;
import com.kemal.spring.domain.User;
import com.kemal.spring.service.RoleService;
import com.kemal.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
	private boolean alreadySetup = false;
	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public DataLoader(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
		Role roleUser = createRoleIfNotFound("ROLE_USER");
		Role roleCollection = createRoleIfNotFound("ROLE_COLLECTION");
		Role roleSudUser = createRoleIfNotFound("ROLE_SUD_USER");
		Role roleSudAdmin = createRoleIfNotFound("ROLE_SUD_ADMIN");
		
		Role roleSurveyAdmin = createRoleIfNotFound("ROLE_SURVEY_ADMIN");
		Role roleSurveyUser = createRoleIfNotFound("ROLE_SURVEY_USER");
		
		Role roleAdministrationAdmin = createRoleIfNotFound("ROLE_ADMINISTRATOR_ADMIN");
		
		Role roleAdministrationUser = createRoleIfNotFound("ROLE_ADMINISTRATOR_USER");
		
		
		List<Role> adminRoles = Collections.singletonList(roleAdmin);
		List<Role> userRoles = Collections.singletonList(roleUser);
		List<Role> collectionRoles = Collections.singletonList(roleCollection);
	
		List<Role> sudUserRoles = Collections.singletonList(roleSudUser);
		List<Role> sudAdminRoles = Collections.singletonList(roleSudAdmin);
		
		List<Role> surveyUserRoles = Collections.singletonList(roleSurveyUser);
		List<Role> surveyAdminRoles = Collections.singletonList(roleSurveyAdmin);
		
		List<Role> administrationAdminRoles = Collections.singletonList(roleAdministrationAdmin);
		
		List<Role> administrationUserRoles = Collections.singletonList(roleAdministrationUser);
		
// Finnobank users
		createUserIfNotFound("sunil.biwal@shubham.co", "Sunil", "Biwal", "sunil.biwal", "sunil.biwal","00556","Gurugram","7048951052","HO – Payment Ops", adminRoles);
		createUserIfNotFound("mohan.singh@shubham.co", "Mohan", "Singh", "mohan.singh", "mohan.singh","05428","Gurugram","7693006877","HO – Payment Ops", adminRoles);
		createUserIfNotFound("Rohit.07498@shubham.co", "Rohit", "07498", "Rohit.07498", "Rohit.07498","07498","Gurugram","7048951305","HO – Payment Ops", adminRoles);
		createUserIfNotFound("varun.tyagi@shubham.co", "varun", "tyagi", "varun.tyagi", "varun.tyagi","03458","Gurugram","8750260017","HO – Collection", collectionRoles);
		
		createUserIfNotFound("amit.lohiya@shubham.co", "amit", "lohiya", "amit.lohiya", "amit.lohiya","02776","Yamuna Vihar","7048951792","Collection", userRoles);
		
		createUserIfNotFound("ASHISH.KHATANA@SHUBHAM.CO", "ASHISH", "KHATANA", "ASHISH.KHATANA", "ASHISH.KHATANA","06112","Gurugram","9711414623","Collection", userRoles);
		createUserIfNotFound("gourav.sharma@shubham.co", "gourav", "sharma", "gourav.sharma", "gourav.sharma","06653","Preet Vihar","9758414051","Collection", userRoles);
		createUserIfNotFound("khimanand.dhoundiyal@shubham.co", "khimanand", "dhoundiyal", "khimanand.dhoundiyal", "khimanand.dhoundiyal","04758","Burari","7048952093","Collection", userRoles);
		createUserIfNotFound("mahesh.kumar@shubham.co", "mahesh", "kumar", "mahesh.kumar", "mahesh.kumar","02459","Faridabad","7048951036","Collection", userRoles);
		createUserIfNotFound("maninder.phogaat@shubham.co", "maninder", "phogaat", "maninder.phogaat", "maninder.phogaat","04809","Kalkaji","7048951647","Collection", userRoles);
		createUserIfNotFound("naveen.jain@shubham.co", "naveen", "jain", "naveen.jain", "naveen.jain","03579","Yamuna Vihar","7048951845","Collection", userRoles);
		createUserIfNotFound("shahzad.khan@shubham.co", "shahzad", "khan", "shahzad.khan", "shahzad.khan","02333","Yamuna Vihar","7048951532","Collection", userRoles);
		createUserIfNotFound("sudhir.gupta@shubham.co", "sudhir", "gupta", "sudhir.gupta", "sudhir.gupta","05748","Mangolpuri Branch","8595521692","Collection", userRoles);
		createUserIfNotFound("sunil.kumar14@shubham.co", "sunil", "kumar14", "sunil.kumar14", "sunil.kumar14","06102","Faridabad","9540004044","Collection", userRoles);
		
		/*New User addred on 12 Aug 2020*/
		createUserIfNotFound("Amandeep.Singh5@shubham.co", "Amandeep", "Singh5", "Amandeep.Singh5", "Amandeep.Singh5","07599","Bhatinda","9041373308","Collection", userRoles);
		createUserIfNotFound("sushil.kumar1@shubham.co", "sushil", "kumar1", "sushil.kumar1", "sushil.kumar1","02794","KARNAL","9215500224","Collection", userRoles);
		
		createUserIfNotFound("Paramjeet.Singh2@shubham.co", "Paramjeet", "Singh2", "Paramjeet.Singh2", "Paramjeet.Singh2","07769","KARNAL","9991005125","Collection", userRoles);
		createUserIfNotFound("tarlochan.singh@shubham.co", "tarlochan", "singh", "tarlochan.singh", "tarlochan.singh","02584","LUDHIANA","9803198880","Collection", userRoles);
		
		createUserIfNotFound("dilshad.ali@shubham.co", "dilshad", "ali", "dilshad.ali", "dilshad.ali","04474","LUDHIANA","9914748315","Collection", userRoles);
		createUserIfNotFound("pardeep.singh@shubham.co", "pardeep", "singh", "pardeep.singh", "pardeep.singh","02773","LUDHIANA","8860626771","Collection", userRoles);
		
		createUserIfNotFound("jatinder.singh1@shubham.co", "jatinder", "singh1", "jatinder.singh1", "jatinder.singh1","06666","LUDHIANA","9781947794","Collection", userRoles);
		createUserIfNotFound("amit.malik@shubham.co", "amit", "malik", "amit.malik", "amit.malik","04228","PANIPAT","8950000508","Collection", userRoles);
		
		createUserIfNotFound("parveen.kumar2@shubham.co", "parveen", "kumar2", "parveen.kumar2", "parveen.kumar2","03378","PANIPAT","9992002006","Collection", userRoles);
		createUserIfNotFound("manjeet.malik@shubham.co", "manjeet", "malik", "manjeet.malika", "manjeet.malik","02970","PANIPAT","8053363003","Collection", userRoles);
		
		
		//Create new Finnobank user on 19 september
		  
		 createUserIfNotFound("shantanu.pal@shubham.co", "Shantanu", "Pal", "shantanu.pal", "shantanu.pal","07074","Lucknow","7048951650","Collection", userRoles);
		 createUserIfNotFound("manojkumar.srivastava@shubham.co", "Manoj", "Srivastava", "manojkumar.srivastava", "manojkumar.srivastava","06150","Lucknow-2","7048951839","Collection", userRoles);
		  createUserIfNotFound("pranayan.dwivedi@shubham.co", "pranayan", "dwivedi", "pranayan.dwivedi", "pranayan.dwivedi","07074","Lucknow -Ayodhya Road","9044672092","Collection", userRoles);
		 createUserIfNotFound("kaushlendra.dwivedi@shubham.co", "kaushlendra", "dwivedi", "kaushlendra.dwivedi", "kaushlendra.dwivedi","04422","Lucknow-2","7827942778","Collection", userRoles);
		  createUserIfNotFound("Vijendra.Dagar@shubham.co", "Vijendra", "Dagar", "Vijendra.Dagar", "Vijendra.Dagar","06347","Jaipur","7597665836","Collection", userRoles);
		 createUserIfNotFound("Praveen.Saini@shubham.co", "Praveen", "Saini", "Praveen.Saini", "Praveen.Saini","06257","Jaipur","9828101972","Collection", userRoles);
		  createUserIfNotFound("rakesh.sharma2@shubham.co", "rakesh", "sharma2", "rakesh.sharma2", "rakesh.sharma2","04411","Jaipur","9784237111","Collection", userRoles);
		 createUserIfNotFound("mohammed.waseem@shubham.co", "Mohammed", "Waseem", "mohammed.waseem", "mohammed.waseem","03977","Jagatpura","7048951635","Collection", userRoles);
		  createUserIfNotFound("Shahbaz.Khan@shubham.co", "Shahbaz", "Khan", "Shahbaz.Khan", "Shahbaz.Khan","06900","Jagatpura","9887399234","Collection", userRoles);
		 createUserIfNotFound("jitendra.sharma1@shubham.co", "Jitendra", "Sharma", "jitendra.sharma1", "jitendra.sharma1","05729","Jagatpura","7014249641","Collection", userRoles);
		 createUserIfNotFound("keshav.maurya@shubham.co", "Keshav Kumar", "Maurya", "keshav.maurya", "keshav.maurya","02557","Jagatpura","9783466690","Collection", userRoles);
		 createUserIfNotFound("swaroop.reddy@shubham.co", "Swaroop", "reddy", "swaroop.reddy", "swaroop.reddy","08444","Hyderabad","8801509555","Collection", userRoles);
		 createUserIfNotFound("nitin.gupta@shubham.co","Nitin ","Gupta","nitin.gupta","nitin.gupta","00802","Jaipur","7048951089","Collection",userRoles);
		 
		 
		
		createUserIfNotFound("Abhinav.Misra@shubham.co", "Abhinav", "Misra", "Abhinav.Misra", "Abhinav.Misra","07781","Gurugram","9205185232","HO – Payment Ops", adminRoles);
		createUserIfNotFound("ajay.kumar10@shubham.co", "ajay", "kumar10", "ajay.kumar10", "ajay.kumar10","08090","Gurugram","9650555380","HO – Payment Ops", adminRoles);
		
		/**/
		createUserIfNotFound("surender.singh@shubham.co", "surender", "singh", "surender.singh", "surender.singh","04193","Gurugram","7048951450","Collection", sudAdminRoles);
		
		//createUserIfNotFound("vijay.uniyal@shubham.co", "vijay", "uniyal", "vijay.uniyal", "vijay.uniyal","05626","Gurugram","8860402323","Collection", sudUserRoles);
		//createUserIfNotFound("himanshu.shandilya@shubham.co","himanshu","shandilya","himanshu.shandilya", "vijay.uniyal","06887","Gurugram","8860402323","Collection", sudUserRoles);
		
		///////////////////////////////////////////////////SUD USER CREDENTIAL///////////////////////////////////////////////////
		   createUserIfNotFound("sumit.kumar10@shubham.co","sumit","kumar10","sumit.kumar10","sumit.kumar10","06521","Preet Vihar","8595521765","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("akash.kumar1@shubham.co","akash","kumar1","akash.kumar1","akash.kumar1","05625","Nehru place","8445840009","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("aqeel.burney@shubham.co","aqeel","burney","aqeel.burney","aqeel.burney","05414","Burari","9811389053","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Navneet.Sharma@shubham.co","Navneet","Sharma","Navneet.Sharma","Navneet.Sharma","07011","Mahavir Enclave","9650668289","Credit Manager",sudUserRoles);
	       createUserIfNotFound("Surender.Kumar3@shubham.co","Surender","Kumar3","Surender.Kumar3","Surender.Kumar3","06988","Shahdra","7531032419","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("baliram.nirala1@shubham.co","baliram","nirala1","baliram.nirala1","baliram.nirala1","06649","MangolPuri","8826456263","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Manoj.Kumar24@shubham.co","Manoj","Kumar24","Manoj.Kumar24","Manoj.Kumar24","07296","Karampura","9058627192","Credit Manager",sudUserRoles);
	       createUserIfNotFound("naveen.grover@shubham.co","naveen","grover","naveen.grover","naveen.grover","05656","Delhi-Dwarka More","9910601915","Disbursement Manager",sudUserRoles);
	       createUserIfNotFound("Istakar.khan1@shubham.co","Istakar","khan1","Istakar.khan1","Istakar.khan1","06625","Delhi-Jhandewalan","9953840540","Disbursement Manager",sudUserRoles);
	       createUserIfNotFound("shailendra.sharma@shubham.co","shailendra","sharma","shailendra.sharma","shailendra.sharma","02616","Ajmer","9610014777","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("bhupendra.mali2@shubham.co","bhupendra","mali2","bhupendra.mali2","bhupendra.mali2","06426","Bhilwara","9214976276","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("hemraj.kumawat@shubham.co","hemraj","kumawat","hemraj.kumawat","hemraj.kumawat","03374","Jaipur","8003818521","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Ravi.Mundra@shubham.co","Ravi","Mundra","Ravi.Mundra","Ravi.Mundra","06443","Jodhpur","9314715902","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Jitendra.Mahawar@shubham.co","Jitendra","Mahawar","Jitendra.Mahawar","Jitendra.Mahawar","07644","Kota","9694055782","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("sameer.singh@shubham.co","sameer","singh","sameer.singh","sameer.singh","01861","Udaipur","8058717120","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("prakhar.khandelwal1@shubham.co","prakhar","khandelwal1","prakhar.khandelwal1","prakhar.khandelwal1","07686","JAGATPURA","9664218629","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Amit.Didwania@shubham.co","Amit","Didwania","Amit.Didwania","Amit.Didwania","07120","Vidhyadhar Nagar","8233909399","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("kailash.sharma@shubham.co","kailash","sharma","kailash.sharma","kailash.sharma","06875","Jaipur-Bagru","9828086348","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Jitendra.Saini@shubham.co","Jitendra","Saini","Jitendra.Saini","Jitendra.Saini","07002","Sikar","8824536247","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Sainath.Lokhande@shubham.co","Sainath","Lokhande","Sainath.Lokhande","Sainath.Lokhande","06970","Ahmednagar","7020983921","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("rohan.shete@shubham.co","rohan","shete","rohan.shete","rohan.shete","04318","Aurangabad","8055511799","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("ajay.ogale@shubham.co","ajay","ogale","ajay.ogale","ajay.ogale","01379","Dhule","8551946140","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Nikhilkumar.Thakur@shubham.co","Nikhilkumar","Thakur","Nikhilkumar.Thakur","Nikhilkumar.Thakur","07565","Jalgaon","8830115564","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("yogesh.patil@shubham.co","yogesh","patil","yogesh.patil","yogesh.patil","04273","Nashik","9665846762","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("virat.thorat@shubham.co","virat","thorat","virat.thorat","virat.thorat","05579","Shrirampur","8149203341","Credit Manager",sudUserRoles);
	       createUserIfNotFound("dhananjay.verma@shubham.co","dhananjay","verma","dhananjay.verma","dhananjay.verma","01247","Allahabad","9204575860","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("gokul.rana@shubham.co","gokul","rana","gokul.rana","gokul.rana","01431","Kanpur","9795809144","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("gaurav.kamra@shubham.co","gaurav","kamra","gaurav.kamra","gaurav.kamra","03832","Lucknow","7754977888","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("himanshu.mishra1@shubham.co","himanshu","mishra1","himanshu.mishra1","himanshu.mishra1","04864","Varanasi","9919766667","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Brijendra.Pandey@shubham.co","Brijendra","Pandey","Brijendra.Pandey","Brijendra.Pandey","07813","Lucknow-2","7651922240","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Rituraj.Mishra@shubham.co","Rituraj","Mishra","Rituraj.Mishra","Rituraj.Mishra","06351","Lucknow-IIM Road","8563860014","Credit Officer",sudUserRoles);
	       createUserIfNotFound("girish.kumar@shubham.co","girish","kumar","girish.kumar","girish.kumar","05110","Lucknow-Ayodhya Road","8115225666","Credit Manager",sudUserRoles);
	       createUserIfNotFound("pandurang.pawar1@shubham.co","pandurang","pawar1","pandurang.pawar1","pandurang.pawar1","07649","Baramati","8087470611","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("aniruddha.ghute1@shubham.co","aniruddha","ghute1","aniruddha.ghute1","aniruddha.ghute1","06919","PIMPRI CHINWAD","9922153411","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("prasad.joshi@shubham.co","prasad","joshi","prasad.joshi","prasad.joshi","05001","Pune","9579314363","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("akshay.mote@shubham.co","akshay","mote","akshay.mote","akshay.mote","05458","Satara","7709720909","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Basweshwar.Dharne1@shubham.co","Basweshwar","Dharne1","Basweshwar.Dharne1","Basweshwar.Dharne1","07087","Keshavnagar","7066880017","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Chetan.Patade@shubham.co","Chetan","Patade","Chetan.Patade","Chetan.Patade","06763","Vasai","8879365088","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Melwyn.Pereira@shubham.co","Melwyn","Pereira","Melwyn.Pereira","Melwyn.Pereira","07213","Palghar","9545311901","Credit Manager",sudUserRoles);
	       createUserIfNotFound("Raushan.Chaudhari@shubham.co","Raushan","Chaudhari","Raushan.Chaudhari","Raushan.Chaudhari","07886","Virar","8451987911","Disbursement Manager",sudUserRoles);
	       createUserIfNotFound("shrutika.muthu@shubham.co","shrutika","muthu","shrutika.muthu","shrutika.muthu","05446","Naigaon","8007797010","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Anshuman.mehta@shubham.co","Anshuman","mehta","Anshuman.mehta","Anshuman.mehta","07585","Ahmedabad","9624919485","Credit Manager",sudUserRoles);
	       createUserIfNotFound("hitesh.ranesha@shubham.co","hitesh","ranesha","hitesh.ranesha","hitesh.ranesha","07287","Surat","8905315371","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Shannu.Shaikh@shubham.co","Shannu","Shaikh","Shannu.Shaikh","Shannu.Shaikh","07024","Wapi","9689111558","Credit Officer",sudUserRoles);
	       createUserIfNotFound("abhinav.vyas@shubham.co","abhinav","","abhinav.vyas","abhinav.vyas","06103","Ahmedabad-Narol","9727805588","Disbursement Officer",sudUserRoles);
	       createUserIfNotFound("naresh.kumar6@shubham.co","naresh","","naresh.kumar6","naresh.kumar6","05530","Patiala","8968566046","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Deepak.Salhotra1@shubham.co","Deepak","","Deepak.Salhotra1","Deepak.Salhotra1","07516","Amritsar","8427855106","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("sanjeev.teji@shubham.co","sanjeev","","sanjeev.teji","sanjeev.teji","02188","Jalandhar","9876427376","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Inderjeet.Bains@shubham.co","Inderjeet","","Inderjeet.Bains","Inderjeet.Bains","07747","Ludhiana","7696580708","Disbursement Manager",sudUserRoles);
	       createUserIfNotFound("jatin.narang@shubham.co","jatin","","jatin.narang","jatin.narang","04559","Mohali","8847567930","Credit Manager",sudUserRoles);
	       createUserIfNotFound("Sumit.Gramini@shubham.co","Sumit","","Sumit.Gramini","Sumit.Gramini","07443","Ambala","8059295916","Disbursement Officer",sudUserRoles);
	       createUserIfNotFound("Ashish.Malik1@shubham.co","Ashish","","Ashish.Malik1","Ashish.Malik1","08014","Karnal","8221004305","Credit Manager",sudUserRoles);
	       createUserIfNotFound("sandeep.ahuja@shubham.co","sandeep","","sandeep.ahuja","sandeep.ahuja","05548","Panipat","9996180999","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("rohit.06617@shubham.co","rohit","","rohit.06617","rohit.06617","06617","Sonipat","8053971373","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Dinesh.Shinde@shubham.co","Dinesh","","Dinesh.Shinde","Dinesh.Shinde","06792","Latur","8208181933","Credit Manager",sudUserRoles);
	       createUserIfNotFound("vishwas.mudgalkar@shubham.co","vishwas","","vishwas.mudgalkar","vishwas.mudgalkar","04780","Parbhani","8888781287","Credit Officer",sudUserRoles);
	       createUserIfNotFound("deepak.ujlambakar@shubham.co","deepak","","deepak.ujlambakar","deepak.ujlambakar","03289","Nanded","8888588533","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("phoolchand.dewangan@shubham.co","phoolchand","","phoolchand.dewangan","phoolchand.dewangan","02155","Bhopal","9691149821","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("sunil.tarani1@shubham.co","sunil","","sunil.tarani1","sunil.tarani1","05142","Indore","9826511121","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("ritesh.kolhe@shubham.co","ritesh","","ritesh.kolhe","ritesh.kolhe","05113","Jabalpur","9424306632","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("vivek.rohan@shubham.co","vivek","","vivek.rohan","vivek.rohan","05716","Sagar","7049951019","Credit Officer",sudUserRoles);
	       createUserIfNotFound("pradeep.madan@shubham.co","pradeep","","pradeep.madan","pradeep.madan","02747","GWALIOR","9650081758","Credit Manager",sudUserRoles);
	       createUserIfNotFound("abhishek.agrahari@shubham.co","abhishek","","abhishek.agrahari","abhishek.agrahari","05783","Indore-Annapurna roa","8817515465","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("shubham.singh@shubham.co","shubham","","shubham.singh","shubham.singh","03880","Agra","7982430004","Disbursement Manager",sudUserRoles);
	       createUserIfNotFound("Shanky.Sahu@shubham.co","Shanky","","Shanky.Sahu","Shanky.Sahu","07564","Jhansi","8953723884","Credit Manager",sudUserRoles);
	       createUserIfNotFound("puneetkumar.duve@shubham.co","puneetkumar","","puneetkumar.duve","puneetkumar.duve","05547","Mathura","9058995681","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("lokesh.kumar@shubham.co","lokesh","","lokesh.kumar","lokesh.kumar","03943","Aligarh","9058383845","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Uma.Sharma@shubham.co","Uma","","Uma.Sharma","Uma.Sharma","06972","Agra-Fatehabad Road","9536971212","Disbursement Manager",sudUserRoles);
	       createUserIfNotFound("Sharad.wuike@shubham.co","Sharad","","Sharad.wuike","Sharad.wuike","06580","Kalyan","8087200667","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("akshay.patil@shubham.co","akshay","","akshay.patil","akshay.patil","03101","Belapur","8652897898","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("ranjita.hatge1@shubham.co","ranjita","","ranjita.hatge1","ranjita.hatge1","04214","Panvel","9833674149","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("divyang.vaiyata@shubham.co","divyang","","divyang.vaiyata","divyang.vaiyata","05511","BADLAPUR","9167062443","Credit Manager",sudUserRoles);
	       createUserIfNotFound("Sandeep.Ingle@shubham.co","Sandeep","","Sandeep.Ingle","Sandeep.Ingle","07336","Dombivali","9867221061","Credit Manager",sudUserRoles);
	       createUserIfNotFound("Sanjay.Singh4@shubham.co","Sanjay","","Sanjay.Singh4","Sanjay.Singh4","07951","Dehradun","7830775636","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Manisha.07861@shubham.co","Manisha","","Manisha.07861","Manisha.07861","07861","Roorkee","9389127580","Executive Trainee",sudUserRoles);
	       createUserIfNotFound("Rohit.Kumar4@shubham.co","Rohit","","Rohit.Kumar4","Rohit.Kumar4","07019","Saharanpur","7895073370","Disbursement Officer",sudUserRoles);
	       createUserIfNotFound("Vinod.Kumar4@shubham.co","Vinod","","Vinod.Kumar4","Vinod.Kumar4","07237","Faridabad","9646478687","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Gaurav.Sinha@shubham.co","Gaurav","","Gaurav.Sinha","Gaurav.Sinha","07669","Gurgaon","9555274056","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("surender.kumar1@shubham.co","surender","","surender.kumar1","surender.kumar1","01935","Rewari","9999029041","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("deepak.kumar7@shubham.co","deepak","","deepak.kumar7","deepak.kumar7","03841","Rohtak","9953205099","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Varsha.Bisht@shubham.co","Varsha","","Varsha.Bisht","Varsha.Bisht","07081","Chhattarpur","7982065967","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("mohd.irshad@shubham.co","mohd","","mohd.irshad","mohd.irshad","04343","Badarpur","7048951093","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("sanjeet.jha@shubham.co","sanjeet","","sanjeet.jha","sanjeet.jha","03773","Delhi-Kapashera","9582208170","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("tausif.shaikh@shubham.co","tausif","","tausif.shaikh","tausif.shaikh","02542","Chandrapur","9021689918","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("ashish.vaidya@shubham.co","ashish","","ashish.vaidya","ashish.vaidya","03670","Nagpur","7798728547","Credit Manager",sudUserRoles);
	       createUserIfNotFound("Sameer.Gaopande@shubham.co","Sameer","","Sameer.Gaopande","Sameer.Gaopande","06483","Akola","8669069515","Credit Officer",sudUserRoles);
	       createUserIfNotFound("Dilip.Tiwaskar@shubham.co","Dilip","","Dilip.Tiwaskar","Dilip.Tiwaskar","07737","Wardha","9371674346","Credit Manager",sudUserRoles);
	       createUserIfNotFound("anjali.jawale@shubham.co","anjali","","anjali.jawale","anjali.jawale","03196","Nagpur-Nelson Square","9767908128","Disbursement Manager",sudUserRoles);
	       createUserIfNotFound("niraj.gujjanwar@shubham.co","niraj","","niraj.gujjanwar","niraj.gujjanwar","06442","Nagpur-Trimurty Nagr","7057676987","Credit Manager",sudUserRoles);
	       createUserIfNotFound("sonal.vajar@shubham.co","sonal","","sonal.vajar","sonal.vajar","00115","Jamnagar","9924336548","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("rambhai.shiyani@shubham.co","rambhai","","rambhai.shiyani","rambhai.shiyani","02458","Junagadh","8849721671","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("ankitkumar.jotaniya@shubham.co","ankitkumar","","ankitkumar.jotaniya","ankitkumar.jotaniya","03479","Rajkot","8469945583","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("subodh.kumar@shubham.co","subodh","","subodh.kumar","subodh.kumar","05431","Meerut","9560430876","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("vipin.kumar2@shubham.co","vipin","","vipin.kumar2","vipin.kumar2","04187","Noida","9870940614","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Alka.sharma@shubham.co","Alka","","Alka.sharma","Alka.sharma","05890","Delhi-Yamuna Vihar","8802572677","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Gagan.Soni@shubham.co","Gagan","","Gagan.Soni","Gagan.Soni","07949","Ghaziabad","7053040365","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Anup.Mishra@shubham.co","Anup","","Anup.Mishra","Anup.Mishra","07201","Delhi-Mayur Vihar","9315413878","Branch Credit Manager",sudUserRoles);
	       createUserIfNotFound("Sumit.Mishra@shubham.co","Sumit","","Sumit.Mishra","Sumit.Mishra","07601","Delhi-Bhogal","8382941095","Credit Manager",sudUserRoles);
	       createUserIfNotFound("Prafula.Nayak@shubham.co","Prafula","","Prafula.Nayak","Prafula.Nayak","06451","Delhi-Naraina","8356004689","Branch Credit Manager",sudUserRoles);

		////////////////////////////////////////////SUD USER CREDENTIAL/////////////////////////////////////////////////////////
		
		//createUserIfNotFound("vijay.uniyal1@shubham.co", "vijay", "uniyal", "vijay.uniyal1", "vijay.uniyal","05626","Gurugram","8860402323","Collection", surveyAdminRoles);
		//createUserIfNotFound("himanshu.shandilya1@shubham.co", "himanshu", "shandilya", "himanshu.shandilya1", "vijay.uniyal","06887","Gurugram","8860402323","Collection", surveyUserRoles);
		
		createUserIfNotFound("bijayendra.jha@shubham.co", "bijayendra", "jha", "bijayendra.jha", "bijayendra.jha","00287","Gurugram","7048951725","Operation", surveyAdminRoles);
		createUserIfNotFound("Kanika.sharma1@shubham.co", "Kanika", "sharma1", "Kanika.sharma1", "Kanika.sharma1","05618","Gurugram","7048951216","Customer Care", surveyUserRoles);
		createUserIfNotFound("bharat.sharma1@shubham.co", "bharat", "sharma1", "bharat.sharma1", "bharat.sharma1","06689","Gurugram","7048951216","Customer Care", surveyUserRoles);
		
		 createUserIfNotFound("Tushar.bhadane@shubham.co","Tushar","bhadane","Tushar.bhadane","Tushar.bhadane","07286","West","9673993880","Credit",sudUserRoles);
		 createUserIfNotFound("atul.shivsharan@shubham.co","atul","shivsharan","atul.shivsharan","atul.shivsharan","08111","Solapur","9922766057","Branch Credit Manager",sudUserRoles);
		 createUserIfNotFound("ashish.rathod@shubham.co","Ashish","Rathod","ashish.rathod","ashish.rathod","08241","Ratlam","9926404767","Credit",sudUserRoles);
		 createUserIfNotFound("ronak.mundra@shubham.co","ronak","mundra","ronak.mundra","ronak.mundra","08265","Udaipur","9001774152","Credit",sudUserRoles);
		 createUserIfNotFound("ram.bisht@shubham.co","Ram","Bisht","ram.bisht","ram.bisht","08393","Moradabad","9718623214","Credit",sudUserRoles);
		 createUserIfNotFound("mukund.mishra@shubham.co","mukund","mishra","mukund.mishra","mukund.mishra","05222","Gorakhpur","9971756062","DO",sudUserRoles);
		 createUserIfNotFound("aman.singh@shubham.co","Aman","Singh","aman.singh","aman.singh","08115","Surat Kadodara","7405359050","Credit",sudUserRoles);	       
		 createUserIfNotFound("hanwant.singh@shubham.co","Hanwant","Singh","hanwant.singh","hanwant.singh","08070","Jodhpur","9829557262","Credit",sudUserRoles);
		 createUserIfNotFound("ranveer.singh1@shubham.co","Ranveer","Singh","ranveer.singh1","ranveer.singh1","08303","Bulandshahr","7055221161","Credit",sudUserRoles);
		///////////////////////////////Administrator Useres//////////////////////
		
		//createUserIfNotFound("abc.xyz@shubham.co", "abc", "xyz", "abc.xyz", "abc.xyz","06690","Gurugram","7048951987","Admin", administrationUserRoles);
		
		createUserIfNotFound("babu.sharma@shubham.co", "Babu", "Sharma", "babu.sharma", "babu.sharma","01278","HO","9971128829","Admin", administrationAdminRoles);
		createUserIfNotFound("rahul.verma@shubham.co", "Rahul", "Verma", "rahul.verma", "rahul.verma","","HO","7048951410","Admin", administrationAdminRoles);
		createUserIfNotFound("sudha.chatterjee@shubham.co", "sudha", "chatterjee", "sudha.chatterjee", "sudha.chatterjee","","Admin","9315357599","Admin", administrationAdminRoles);
		createUserIfNotFound("bharat.makhija@shubham.co", "bharat", "makhija", "bharat.makhija", "bharat.makhija","","FARIDABAD","9911206028","Sales", administrationUserRoles);
		createUserIfNotFound("animesh.kumar@shubham.co", "Animesh", "Kumar", "animesh.kumar", "animesh.kumar","","Kalkaji","8421450930","Sales", administrationUserRoles);
		
		alreadySetup = true;
	}

	@Transactional
	Role createRoleIfNotFound(final String name) {
		Role role = roleService.findByName(name);
		if (role == null) {
			role = new Role(name);
			roleService.save(role);
		}
		return role;
	}

	@Transactional
	void createUserIfNotFound(
			final String email,final String name, final String surname, final String username,final String password,
			final String empcode, final String empbranch, final String empcontactno, final String empdepartment, final List<Role> userRoles
		
		
			) {
		User user = userService.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setName(name);
			user.setSurname(surname);
			user.setUsername(username);
			user.setPassword(bCryptPasswordEncoder.encode(password));
			user.setEmail(email);
			user.setRoles(userRoles);
			user.setCreatedon(new Date());
			user.setEmpbranch(empbranch);
			user.setEmpcode(empcode);
			user.setEmpdepartment(empdepartment);
			user.setEmpcontactno(empcontactno);
			
			user.setEnabled(true);
			userService.save(user);
		}
	}
}