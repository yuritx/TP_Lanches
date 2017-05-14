package dados;

import java.util.Scanner;

public class LanchesTrasactionScript {

	
private int produtos [];
private int count;

//test
public LanchesTrasactionScript(){
	
	int [] produtos = new int[100];
	
}

public void realizaVenda(){
	count = 0;
	int x =1;
	while(x!=0){
	System.out.println("digite o codigo do produto" + "/n" + "Digite 0 para sair");
	
	Scanner ler = new Scanner(System.in);
	x = ler.nextInt();
	
	//if (x==0){ EmitirNotafiscal(int[] produtos);}
	produtos[count]  = x;
	System.out.println("digite a quatidade do produto");
	produtos[count +1]  = ler.nextInt();
	count +=2;
	
	
	
	}
	
	
	
	
}






}
