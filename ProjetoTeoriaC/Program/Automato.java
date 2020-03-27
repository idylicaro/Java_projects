package Program;

import java.util.ArrayList;
import java.util.List;

public class Automato {
    public List<Estado> listaDeEstados;

    public List<Estado> geraAutomato(EQ[] tabela, List<Estado> e) {
        List<Estado> automato = new ArrayList<>();
        List<Estado> unificados = new ArrayList<>();

        //program.displayListaEstados(e);

        int iNewEstate = 0;
        //tirando os estados solitarios
        for (int x=0;x< e.size();x++) {
            boolean allX = true;
            for (EQ eq: tabela) {
                if((e.get(x).getNome().equals(eq.a.getNome()) || e.get(x).getNome().equals(eq.b.getNome())) && eq.equivalente == '0'){
                    allX = false;
                    break;
                }
            }
            if (allX){
                int indexMew = iNewEstate++;
                Estado lestado ;
                lestado = e.get(x);
                e.remove(x--);
                lestado.setId(indexMew);
                for (Transicao t: lestado.transicoes) {
                    t.setIdOrigem(indexMew);
                }
                unificados.add(lestado);
            }
        }

        //retirando estados da tabela
        for (int i = 0; i < tabela.length; i++) {
            //pegando estados equivalentes
            if (tabela[i].equivalente == '0') {
                Estado aux = new Estado();
                Transicao taux;
                aux.setId(iNewEstate);
                aux.setNome(tabela[i].indexEstadoCombinacao);
                if (tabela[i].getA().isStatusFinal()) {
                    aux.setStatusFinal(true);
                }
                if (tabela[i].getA().isStatusInicial() || tabela[i].getB().isStatusInicial()) {
                    aux.setStatusInicial(true);
                }
                //combt's
                for (int j = 0; j < tabela[i].combT.length; j++) {
                    taux = new Transicao();
                    // precisa automatizar esse processo de adicionar Transições e tambem as combinações
                    taux.simbolo = tabela[i].simbolos[j];
                    taux.setIdOrigem(iNewEstate);
                    //remove char repetido
                    StringBuilder buffer = new StringBuilder((tabela[i].combT[j]));
                    for (int r = 0; r < tabela[i].combT[j].length(); r++) {
                        for (int y = 1; y < tabela[i].combT[j].length(); y++) {
                            if (tabela[i].combT[j].charAt(r) == tabela[i].combT[j].charAt(y) && r != y) {
                                buffer.setCharAt(y, 'X');
                            }
                        }
                    }

                    tabela[i].combT[j] = String.valueOf(buffer);
                    String xct1 = tabela[i].combT[j].replaceAll("X", "");
                    tabela[i].combT[j] = xct1;

                    taux.setIdDestino(tabela[i].combT[j]);

                    aux.transicoes.add(taux);
                }
                unificados.add(aux);
            }
            iNewEstate++;
        }

        //unifica estados ja unificados
        for (int i = 0;i< unificados.size();i++){
            for (int j = i;j<unificados.size();j++){
                if(i == j){
                    continue;
                }

                if(unificados.get(i).nome.contains(unificados.get(j).nome)){
                    unificados.remove(j);
                    break;
                }
                if(unificados.get(i).nome.length() != unificados.get(j).nome.length()){
                    break;
                }
                for(int x =0;x< unificados.get(i).getNome().length();x++){
                      if(containNome(unificados.get(i).nome.charAt(x),unificados.get(j).nome)){
                        String auxChar = String.valueOf(unificados.get(i).nome.charAt(x));
                        unificados.get(i).nome += unificados.get(j).nome.replace(auxChar,"");
                        unificados.remove(j);
                        break;
                    }
                }
            }
        }

        //MUDA AS transições para os id de estados atuais
        for (Estado estado:unificados) {
            for (Transicao transicao: estado.transicoes){
                Boolean boolResu = true;
                for (int i = 0; i <unificados.size() ; i++) {
                    char auxxx [] = transicao.getIdDestino().toCharArray();
                    if(unificados.get(i).getNome().contains(transicao.getIdDestino())){
                        transicao.setIdDestino(String.valueOf(unificados.get(i).getId()));
                        boolResu = false;
                        break;
                    }
                }
                if (boolResu){
                    for (int i = 0; i <unificados.size() ; i++) {
                        if(containsCharinString(transicao.getIdDestino(),unificados.get(i).getNome())){
                            transicao.setIdDestino(String.valueOf(unificados.get(i).getId()));
                            break;
                        }
                    }
                }
            }
        }


        //remove estados inuteis
        for (int i = 0; i < unificados.size(); i++) {
            boolean r = false;
            for (int u = 0; u < unificados.size(); u++) {
                for (Transicao tr : unificados.get(u).transicoes) {
                    if (unificados.get(i).getId() == Integer.parseInt(tr.idDestino) || unificados.get(i).isStatusInicial()) {
                        r = true;
                        break;
                    }
                }
            }
            if (!r) {
                unificados.remove(i--);
            }
        }




        return unificados;
    }
    public boolean containsCharinString(String a,String b){
        String[] crs = new String[a.length()];
        // separa string em um vetor
        for (int i = 0;i < crs.length;i++){
            crs[i] = String.valueOf(a.charAt(i));
        }
        for (int i=0;i< crs.length;i++){
            if(b.contains(crs[i]))
                return true;
        }
        return false;
    }

    public static boolean containId(int idOrigem, int idCompare) {
        // verifica se o id alguma parte do numero está contido ne algum id
        String sIdOrigem = String.valueOf(idOrigem);
        String sIdCompare = String.valueOf(idCompare);
        for (int i = 0; i < sIdCompare.length(); i++) {
            if (sIdOrigem.charAt(0) == sIdCompare.charAt(i)) {
                return true;
            }
        }
        return false;
    }
    public static boolean containNome(char idOrigem, String idCompare) {
        // verifica se o id alguma parte do numero está contido ne algum id
        String sIdOrigem = String.valueOf(idOrigem);
        String sIdCompare = String.valueOf(idCompare);
        for (int i = 0; i < sIdCompare.length(); i++) {
                if (sIdOrigem.charAt(0) == sIdCompare.charAt(i)) {
                    return true;
                }
        }
        return false;
    }

    public static boolean isSubstring(){
        return false;
    }
}
