package Program;

import java.util.List;

public class EQ {
    String indexEstadoCombinacao;
    Estado a;
    Estado b;
    char equivalente;
    // o index do resultado das Transiçoes entre combinações
    String[] combT ;
    // simbolo da transição
    String[] simbolos;


    public EQ(Estado a, Estado b, char equivalente) {
        this.a = a;
        this.b = b;
        this.equivalente = equivalente;
        indexEstadoCombinacao = (a.nome + b.nome);
        simbolos= new String[a.transicoes.size()];
        for (int i = 0;i<simbolos.length;i++){
                simbolos[i] = a.transicoes.get(i).getSimbolo();
        }
        combT = new String[a.transicoes.size()];
        for (int i = 0; i <combT.length ; i++) {
            combT[i] = String.valueOf(String.valueOf(a.transicoes.get(i).getIdDestino()) + String.valueOf(b.transicoes.get(i).getIdDestino()));
        }
        }

    public static EQ[] prencherEQ(List<Estado> estadosFromXml) {
        //(X*X-1)/2
        int tamTabela = estadosFromXml.size();
        tamTabela = (tamTabela * (tamTabela - 1)) / 2;
        EQ[] tabela = new EQ[tamTabela];
        int zelta = 0;
        Estado A, B;
        EQ eq;
        //for prenchedor da tabela
        int x = 0, j;
        for (; x < estadosFromXml.size() - 1; x++) {
            for (j = x + 1; j < estadosFromXml.size(); j++) {
                A = estadosFromXml.get(x);
                B = estadosFromXml.get(j);
                eq = new EQ(A, B, '0');
                tabela[zelta++] = eq;
            }
        }
        //passo 2
        naoTriviaisEquivalentes(tabela);
        return tabela;
    }

    public static void naoTriviaisEquivalentes(EQ[] tabela) {
        // como começa por zero tem que ser -1 no caso '<'
        for (int i = 0; i < tabela.length; i++) {
            if ((tabela[i].a.isStatusFinal() && !tabela[i].b.isStatusFinal()) || (!tabela[i].a.isStatusFinal() && tabela[i].b.isStatusFinal())) {
                tabela[i].equivalente = 'x';
            }
        }
    }

    public static void processamento(EQ[] tabela) {
        //passo 3

        int action;

        do {
            action = 0;
            for (int i = 0; i < tabela.length; i++) {

                if (tabela[i].equivalente == '0') {
                    for (int x = 0; x < tabela.length; x++) {
                        if (i == x) {
                            continue;
                        }

                        // ( ((a|b)|(a|b)) && x )
                        for (int j = 0; j < tabela[i].combT.length; j++) {
                            if( tabela[i].combT[j].equals(tabela[x].indexEstadoCombinacao) && tabela[x].equivalente != '0'){
                                tabela[i].equivalente = '+';
                                action++;
                                break;
                            }
                        }

                    }//fim for x
                }
            }//fim for i
            
        } while (action != 0);

    }

    public Estado getA() {
        return a;
    }

    public void setA(Estado a) {
        this.a = a;
    }

    public Estado getB() {
        return b;
    }

    public void setB(Estado b) {
        this.b = b;
    }

    public char isEquivalente() {
        return equivalente;
    }

    public void setEquivalente(char equivalente) {
        this.equivalente = equivalente;
    }
    public boolean compareToStringComb(String x){
        for (int i = 0; i< indexEstadoCombinacao.length(); i++){
            if(indexEstadoCombinacao.charAt(i) == x.charAt(0))
                return true;
        }

        return false;
    }
}
