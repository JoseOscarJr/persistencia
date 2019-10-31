package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grafico {
	
	public void teste(List<Pessoa> listaPessoa) throws IOException{
		String[] nomeDominio = new String[listaPessoa.size()];
		String[] nomeDominioFinal = new String[listaPessoa.size()];
		int cont = 0;
		for (Pessoa pessoa2 : listaPessoa) {
			String dominio[] = pessoa2.getEmail().split("@");
			nomeDominio[cont] = dominio[1];
			nomeDominioFinal[cont] = dominio[1];
			cont++;
		}
		String[] nomeDominioAux = new String[nomeDominio.length];
		nomeDominioAux = nomeDominio;

		for (int i = 0; i < nomeDominio.length; i++) {
			for (int j = i; j < nomeDominioAux.length-1; j++) {
				if(nomeDominio[i].equalsIgnoreCase(nomeDominioAux[j+1])) {
					nomeDominioAux[j+1] = "-";
				}
			}
		}
		cont = 0;
		for (int i = 0; i < nomeDominioAux.length; i++) {
			if(nomeDominioAux[i] != "-") {
				cont++;
			}
		}
		String[] vetorNomesDominios= new String[cont];
		int[] vetorquantidadeDominios= new int[cont];
		cont = 0;
		for (int i = 0; i < nomeDominioAux.length; i++) {
			if(nomeDominioAux[i] != "-") {
				vetorNomesDominios[cont] = nomeDominioAux[i];
				cont++;
			}
		}
		for (int i = 0; i < nomeDominioFinal.length; i++) {
			System.out.println(nomeDominioFinal[i]);
		}
		for (int i = 0; i < vetorquantidadeDominios.length; i++) {
			cont = 0;
			for (int j = 0; j < nomeDominio.length; j++) {
				if(vetorNomesDominios[i].equals(nomeDominioFinal[j])) {
					cont++;
				}
				
			}
			vetorquantidadeDominios[i]= cont;
		}
		
	 FileWriter arq = new FileWriter("GoogleCharts.HTML");
	 PrintWriter gravarArq = new PrintWriter(arq);
	 
	    gravarArq.printf("<html>\r\n" + 
	    		"  <head>\r\n" + 
	    		"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
	    		"    <script type=\"text/javascript\">\r\n" + 
	    		"      google.charts.load('current', {'packages':['corechart']});\r\n" + 
	    		"      google.charts.setOnLoadCallback(drawChart);\r\n" + 
	    		"\r\n" + 
	    		"      function drawChart() {\r\n" + 
	    		"\r\n" + 
	    		"        var data = google.visualization.arrayToDataTable([\r\n" +
	    		"          ['Dominios', 'Quantidades'],\r\n"); 
	    		for (int i = 0; i < vetorNomesDominios.length; i++) {
					gravarArq.printf("['"+vetorNomesDominios[i]+"', "+vetorquantidadeDominios[i]+"],\r\n");
					System.out.println();
	   
	    		}
	    		gravarArq.printf(" ]);\r\n" +
	    		"\r\n" + 
	    		"        var options = {\r\n" + 
	    		"          pieHole: 0.5,\r\n" + 
	    		"          pieSliceTextStyle: {\r\n" + 
	    		"            color: 'black',\r\n" + 
	    		"          },\r\n" + 
	    		"          legend: 'Turmas'\r\n" + 
	    		"        };\r\n" + 
	    		"\r\n" + 
	    		"        var chart = new google.visualization.PieChart(document.getElementById('donut_single'));\r\n" + 
	    		"        chart.draw(data, options);\r\n" + 
	    		"      }\r\n" + 
	    		"    </script>\r\n" + 
	    		"  </head>\r\n" + 
	    		"  <body>\r\n" + 
	    		"    <div id=\"donut_single\" style=\"width: 900px; height: 500px;\"></div>\r\n" + 
	    		"  </body>\r\n" + 
	    		"</html>");
	    arq.close();

}
}
