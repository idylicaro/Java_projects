package Program;

import java.util.ArrayList;
import java.util.List;

public class program {

    public static void main(String[] args) {
        int [] am = new int[3];

        int automatoOrigemTamanho,automatoDepoisTamanho ,automatoDepoisTamanho2=0,testMudanca =0;
        ReadXML cxml = new ReadXML();
        // passo 1
        int tamTabela;
        List<Estado> estadosFromXml = cxml.extract("C:\\Users\\Dracula Castle\\Desktop\\tcex\\test3.jff");
        //List<Estado> estadosFromXml = prencheListaTeste1();
        //sempre que carregar uma lista de estados organizar e padronizar suas transiçções
        for (Estado e: estadosFromXml) {
            e.sortTransicoes();
        }

        tamTabela = estadosFromXml.size();
        automatoOrigemTamanho = tamTabela;
        //(X*X-1)/2
        tamTabela = (tamTabela*(tamTabela-1))/2;

        //passo 2 está dentro da função prencherEQ
        EQ[] tabela = EQ.prencherEQ(estadosFromXml);
        displayTabela(tamTabela, tabela);

        EQ.processamento(tabela);

        displayTabela(tamTabela, tabela);

        Automato automato = new Automato();
        automato.listaDeEstados = automato.geraAutomato(tabela,estadosFromXml);
        displayAutomato(automato);
        WriteXML.geraJff(automato,"C:\\Users\\Dracula Castle\\Desktop\\resultadosProject\\ex3.jff");

    }

    public static void displayAutomato(Automato automato) {
        for (Estado e : automato.listaDeEstados) {
            System.out.println(e.toString());
            System.out.println("Transição:");
            for (Transicao t : e.transicoes) {
                System.out.println(t.toString());
            }
            System.out.println("------------------------------------------------------------------------------------");
        }
    }

    private static void displayTabela(int tamTabela, EQ[] tabela) {
        for (int i=0;i<tamTabela;i++){
            System.out.println(i);
            System.out.println("A : "+tabela[i].a.toString()+"\n\n"+"B : "+tabela[i].b.toString()+"\n"+tabela[i].equivalente+"\n");
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
    }
    public static void displayListaEstados(List<Estado> estadoList){
        for (Estado e: estadoList ) {
            System.out.println(e.toString());
            for (Transicao t: e.transicoes) {
                System.out.println(t.toString()+"\n");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
            System.out.println("---------------------------------------------------------------");
        }
    }
    public static List<Estado> prencheListaTeste1(){
        //ex 2
        List<Estado> estadoList = new ArrayList<>();
        Estado e;
        Transicao t;
        e = new Estado(0,"0",true,true);
        t = new Transicao(0,"1", "a");
        e.transicoes.add(t);
        t = new Transicao(0,"2", "b");
        e.transicoes.add(t);
        estadoList.add(e);

        e = new Estado(1,"1",false,false);
        t = new Transicao(1,"1", "a");
        e.transicoes.add(t);
        t = new Transicao(1,"3", "b");
        e.transicoes.add(t);
        estadoList.add(e);

        e = new Estado(2,"2",false,false);
        t = new Transicao(2,"2", "a");
        e.transicoes.add(t);
        t = new Transicao(2,"3", "b");
        e.transicoes.add(t);
        estadoList.add(e);

        e = new Estado(3,"3",true,false);
        t = new Transicao(3,"1", "a");
        e.transicoes.add(t);
        t = new Transicao(3,"3", "b");
        e.transicoes.add(t);
        estadoList.add(e);

        return estadoList;
    }
}
