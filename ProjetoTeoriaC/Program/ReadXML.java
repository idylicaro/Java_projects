package Program;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadXML {
    public DocumentBuilderFactory factory;
    public DocumentBuilder biulder;

    public ReadXML() {
        factory = DocumentBuilderFactory.newInstance();
        try {
            biulder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e);
        }
    }

    public List<Estado> extract(String path){
        List<Estado> automato = new ArrayList<>();
        List<Transicao> listTransicoes = new ArrayList<>();
        Estado e;
        Transicao t;
        try {
            Document doc = biulder.parse(path);

            //node list e cada parent do xml
            NodeList estados = doc.getElementsByTagName("state");
            int tamEstadosNodeList = estados.getLength();

            for (int i = 0;i<tamEstadosNodeList;i++){
                e = new Estado();
                Node noEstado = estados.item(i);
                //verifica se é um no tipo elemento
                if(noEstado.getNodeType() == Node.ELEMENT_NODE){
                    Element elementoEstado = (Element) noEstado;


                    e.setId(Integer.parseInt(elementoEstado.getAttribute("id")));
                    e.setNome((elementoEstado.getAttribute("name")).substring(1));

                    //aqui vai ser a lista de elementos dentro de estado
                    NodeList childrensOfState = elementoEstado.getChildNodes();
                    int tamChildrensOfState = childrensOfState.getLength();
                    for(int z = 0 ; z< tamChildrensOfState;z++){
                        Node noFilho = childrensOfState.item(z);
                        //verificando se esse tipo de no e element
                        if(noFilho.getNodeType() == Node.ELEMENT_NODE){
                            Element elementChild = (Element) noFilho;

                            switch (elementChild.getTagName()){
                                case "initial":
                                    e.setStatusInicial(true);
                                    break;
                                case "final":
                                    e.setStatusFinal(true);
                                    break;
                            }

                        }
                    }

                }
                automato.add(e);
            }//fim do for estado

            //prenche Transições;
            NodeList transicoes = doc.getElementsByTagName("transition");
            int quantTransicoes = transicoes.getLength();
            for (int x = 0; x< quantTransicoes;x++){
                t = new Transicao();
                Node transicaoPai = transicoes.item(x);
                if (transicaoPai.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementTPai = (Element) transicaoPai;

                    NodeList elementsOfTPai = elementTPai.getChildNodes();
                    for (int q = 0; q < elementsOfTPai.getLength(); q++) {
                        Node atributFilho = elementsOfTPai.item(q);
                        if (atributFilho.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementAFilho = (Element) atributFilho;
                            switch (elementAFilho.getTagName()){
                                case "from":
                                    t.setIdOrigem(Integer.parseInt(elementAFilho.getTextContent()));
                                    break;
                                case "to":
                                    String b = elementAFilho.getTextContent();
                                    t.setIdDestino(b);
                                    break;
                                case "read":
                                    String a = elementAFilho.getTextContent();
                                    t.setSimbolo(a);
                                    break;
                            }
                        }
                    }
                }
                listTransicoes.add(t);
            }

            //add cada transicao ao seu estado
            for (Estado estate: automato) {
                for (Transicao tra : listTransicoes){
                    if (tra.getIdOrigem() == estate.getId())
                        estate.transicoes.add(tra);
                }
            }

        } catch (SAXException | IOException ex) {
            System.out.println(ex);
        }
        return automato;
    }

}
