package com.fundamentosplatzi.fundamentos;

import com.fundamentosplatzi.fundamentos.bean.MyBean;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	final ComponentDependency componentDependency;
	final MyBean myBean;
	final MyBeanWithDependency myBeanWithDependency;
	final MyBeanWithProperties myBeanWithProperties;
	final UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args){
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional(){
		User test1 = new User("testTransactional1", "testTransactional1@gmail.com",LocalDate.of(2022,04,20));
		User test2 = new User("testTransactional2", "testTransactional2@gmail.com",LocalDate.of(2022,04,21));
		User test3 = new User("testTransactional3", "testTransactional1@gmail.com",LocalDate.of(2022,04,22));
		List<User> users = Arrays.asList(test1,test2,test3);

		try{
			userService.saveTransactional(users);
		}catch (Exception e) {
			LOGGER.error("Esta es una excepcion dentro del metodo transaccional " + e);
		}
		userService.getAllUsers().stream()
				.forEach(user ->
						LOGGER.info("Este es el usuarioo dentro del metodo transactional " + user));


	}

	private void getInformationJpqlFromUser(){
		/*LOGGER.info("Usuario con el metodo findByUserEmail"+
				userRepository.findByUserEmail("cesar.acjota@tecsup.edu.pe")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
		userRepository.findAndSort("user", Sort.by("id").descending())
				.forEach(user -> LOGGER.info("Usuario con metodo sort "+user));
		userRepository.findByName("Cesar")
		.forEach(user -> LOGGER.info("Usuario con query method"+user));
		LOGGER.info("Usuario con query method findByEmailAndName "+userRepository.findByEmailAndName("monica@tecsup.edu.pe","Monica")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));
		userRepository.findByNameLike("%u%")
				.forEach(user -> LOGGER.info("Usuario findByNameLike" + user));
		userRepository.findByNameOrEmail(null,"monica@tecsup.edu.pe")
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail" + user));
*/
		userRepository.findByBirthDateBetween(LocalDate.of(2021,3,1), LocalDate.of(2021,3,2))
				.forEach(user -> LOGGER.info("Usuario con intervalo de fecha "+user));
		LOGGER.info("Es usuario a partir del named parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,03,2), "rony.acjota@tecsup.edu.pe")
				.orElseThrow(() -> new RuntimeException("no se encontro el usuario a partir del named parameter")));
	}
	private void saveUsersInDataBase(){
		User user1 = new User("Cesar", "cesar.acjota@tecsup.edu.pe", LocalDate.of(2022,04,20));
		User user2 = new User("Monica", "monica@tecsup.edu.pe", LocalDate.of(2022,03,19));
		User user3 = new User("user7", "juan.acjota@tecsup.edu.pe", LocalDate.of(2022,04,22));
		User user4 = new User("user2", "javier.acjota@tecsup.edu.pe", LocalDate.of(2022,04,23));
		User user5 = new User("Roni", "rony.acjota@tecsup.edu.pe", LocalDate.of(2021,03,2));
		User user6 = new User("Gaby", "gaby.acjota@tecsup.edu.pe", LocalDate.of(2022,04,25));
		User user7 = new User("Tito", "tito.acjota@tecsup.edu.pe", LocalDate.of(2022,04,26));
		User user8 = new User("Fabry", "fabry.acjota@tecsup.edu.pe", LocalDate.of(2022,04,27));
		User user9 = new User("user4", "john.acjota@tecsup.edu.pe", LocalDate.of(2022,04,28));
		User user10 = new User("Karla", "karla.acjota@tecsup.edu.pe", LocalDate.of(2022,04,20));
		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);
	}
	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
		try {
			int value = 10/0;
			LOGGER.info("Mi variable: "+value);
		}catch (Exception e){
			LOGGER.error("This is an error to divide for 0"+e.getStackTrace());
		}
	}
}
