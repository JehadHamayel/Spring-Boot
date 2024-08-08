package com.jehadscode.database;

import com.jehadscode.database.dao.impl.AuthorDaoImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Log
public class DatabaseApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

}
