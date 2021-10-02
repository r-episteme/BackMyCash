package com.mycash.mycash;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mycash.mycash.model.Despesa;
import com.mycash.mycash.repository.DespesaRepository;

@SpringBootApplication
public class MycashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycashApplication.class, args);
	}

}

	/*
@Bean
CommandLineRunner init(DespesaRepository despesaRepository) {
		return args -> {
//			despesaRepository.deleteAll();
//			receitaRepository.deleteAll();
			LongStream.range(1, 10)
			.mapToObj(i-> {
				Despesa desp = new Despesa();
				desp.setData(i + i + "/" + i + i );
				desp.setDescricao("TAKARO");
				desp.setFixo(true);
				desp.setTipo("Outros");
				desp.setValor( "R$: " + i + i + i + i );
				return desp;
			})
			.map(m -> despesaRepository.save(m))
			.forEach(System.out::println);
		};
  } 
	
	
	
	@Bean
	CommandLineRunner init(ReceitaRepository receitaRepository) {
			return args -> {
				LongStream.range(1, 100)
				.mapToObj(i-> {
					Receita rec = new Receita();
					rec.setData(i + i + "/" + i + i );
					rec.setDescricao("TABOA");
					rec.setFixo(true);
					rec.setTipo("Outros");
					rec.setValor( "R$: " + i + i + i + i );
					return rec;
				})
				.map(m -> receitaRepository.save(m))
				.forEach(System.out::println);
			};
	  } */
	
	
