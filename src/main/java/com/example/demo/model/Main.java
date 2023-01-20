package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.HistoRepository;

public class Main {

	@Autowired
    HistoRepository histoRepository;
	
	public static void main(String[] args) {
		
	}

}
