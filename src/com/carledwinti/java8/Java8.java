package com.carledwinti.java8;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Java8{

	public static void main(String[] a) {


		List<String> palavras = Arrays.asList("bola2", "um", "quatro", "casa5", "aluno1", "aluno2", "aluno3");

		System.out.println("Lista inicial: " + palavras);

		sortComCollectionSort(palavras);

	}

	public static void sortComCollectionSort(List<String> palavras){

		//sem lambda
		//Comparator<String> comparator = comparatorComClasseAnonima();

		//com lambda
		/*
			System.out.println("Executando comparator com Lambda... ");
			Comparator<String> comparator = (s1, s2) -> {
				return Integer.compare(s1.length(), s2.length());
			};
		*/

		/*
			System.out.println("Executando comparator com Lambda .... codigo reduzido 1 ");
			Comparator<String> comparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
		 */


		//Collections.sort(palavras, comparator);
		//System.out.println("Sem sort java8 >>> " + palavras);


		//em java 8
		//passando o comparator para o sort da lista
		//palavras.sort(comparator);

		//implementando o comparator com lambda diretamente na passagem de parametro para o sort da lista
		System.out.println("executando o sort com passagem de lambda como parametro");
		palavras.sort((s1,s2) -> Integer.compare(s1.length(), s2.length()));

		System.out.println("Com sort java8 >>> " + palavras);

		new Thread(() -> System.out.println("Nova thread rodando")).start();

		System.out.println("Imprimendo elementos da lista com foreach e lambda....");
		palavras.forEach(elemento -> System.out.println("Elemento >>>> " + elemento));

		List<String> letras = Arrays.asList("UUUUUU", "ZZZZZ", "DD", "FFF","J", "GGG");

		System.out.println("Lista inicial: ");
		System.out.println(letras);

		letras = ordenaListaComLamdaEComparator(letras);

		System.out.println("Lista final: ");
		System.out.println(letras);

		List<String> frutas1 = Arrays.asList("Melancia", "Uva", "Maca", "Banana", "Pera", "Carambola", "Jaca");
		List<String> frutas2 = Arrays.asList("Laranja", "Framboesa", "Tamara", "Goiaba Branca");
		List<String> frutas3 = Arrays.asList("Nectarina", "Morango", "Jamelao");

		System.out.println("Trabalhando com Functions Lambda");
		Function<String, Integer> functionCompleta = (String s) -> s.length();
		Function<String, Integer> functionNormal = s -> s.length();
		Function<String, Integer> functionReduzida = String::length;


		System.out.println("Lista de Frutas 1 inicial: " + frutas1);
		System.out.println("Lista de Frutas 2 inicial: " + frutas2);
		System.out.println("Lista de Frutas 3 inicial: " + frutas3);

		//frutas1.sort(Comparator.comparing(functionCompleta));
		//frutas2.sort(Comparator.comparing(functionNormal));
		//frutas3.sort(Comparator.comparing(functionReduzida));

		System.out.println("Passando a function diretamente por parametro ao Comparator.comparing");
		frutas1.sort(Comparator.comparing((String s) -> s.length()));
		frutas2.sort(Comparator.comparing(s -> s.length()));
		frutas3.sort(Comparator.comparing(String::length));

		System.out.println("Lista de Frutas 1 final apos fucntion completa: " + frutas1);
		System.out.println("Lista de Frutas 2 final apos function normal: " + frutas2);
		System.out.println("Lista de Frutas 3 final apos function reduzida: " + frutas3);


		System.out.println("Filtrando Collections com stream e filter somente para palavras com length ate 4 ");

		List<String> nomes = Arrays.asList("Fabiana", "Paloma", "Tatiana", "Jaqui", "Elen");

		System.out.println("Lista de nomes inicial: \n" + nomes);

		System.out.println("Lista de nomes Filtrada com stream e filter que recebe um Predicate explicito se for igual a Jaqui com forEach que recebe uma function para imprimir os nomes");

		nomes.stream()
				.filter(filtraPeloNome())
				.forEach(System.out::println);

		System.out.println("Lista de nomes Filtrada com stream e filter que recebe um Predicate implicito com forEach que recebe uma function para imprimir os nomes");
		nomes.stream()
				.filter(s -> s.length() <= 4)
				.forEach(System.out::println);

		System.out.println("Utilizando MAP para transformar listas com stream.map ");
		Stream<Integer> streamNomes = nomes.stream().map(String::length);
		System.out.println("Lista de nomes mapeada pelo length dos nomes");
		System.out.println(streamNomes.toString());

		System.out.println("Criando MAP com IntStream para evitar o boxing");
		IntStream intStreamNomes = nomes.stream().mapToInt(String::length);


	}

	public static Predicate<String> filtraPeloNome(){
		return p -> p.equals("Jaqui");
	}

	public static List<String> ordenaListaComLamdaEComparator(List<String> letras){

		System.out.println("Executando ordenação da lista somente com lambda de maneira mais sucinta...");

		letras.sort(Comparator.comparing(string -> string.length()));
		return letras;
	}


	public static Comparator<String> comparatorComClasseAnonima() {

		System.out.println("Executando comparator sem Lambda... ");

		return new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		};
	}


}