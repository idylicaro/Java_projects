package Program;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class WriteXML {

    public static void geraJff(Automato automato, String path){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document documentXML = documentBuilder.newDocument();
            Element structure = documentXML.createElement("structure");

            documentXML.appendChild(structure);
            Element type = documentXML.createElement("type");
            type.appendChild(documentXML.createTextNode("fa"));

            structure.appendChild(type);

            Element automaton = documentXML.createElement("automaton");
            structure.appendChild(automaton);
            String id_estado = "";

            for (int i = 0; i < automato.listaDeEstados.size(); i++) {
                String auxc = "";
                Element state = documentXML.createElement("state");

                Attr id = documentXML.createAttribute("id");
                Attr name = documentXML.createAttribute("name");

                id_estado = Integer.toString(automato.listaDeEstados.get(i).getId());
                id.setValue(id_estado);
                name.setValue("q" + id_estado);

                state.setAttributeNode(id);
                state.setAttributeNode(name);

                Element eixoX = documentXML.createElement("x");
                eixoX.appendChild(documentXML.createTextNode("309.0"));
                state.appendChild(eixoX);

                Element eixoY = documentXML.createElement("y");
                eixoY.appendChild(documentXML.createTextNode("221.0"));
                state.appendChild(eixoY);

                Element label = documentXML.createElement("label");
                String aux = automato.listaDeEstados.get(i).getNome().replaceAll("q", "");
                label.appendChild(documentXML.createTextNode(aux));
                state.appendChild(label);

                if (automato.listaDeEstados.get(i).isStatusFinal()) {
                    Element final_estado = documentXML.createElement("final");
                    state.appendChild(final_estado);

                }
                if (automato.listaDeEstados.get(i).isStatusInicial()) {
                    Element inicial = documentXML.createElement("initial");
                    state.appendChild(inicial);
                }
                automaton.appendChild(state);

            }
            for (Estado estate : automato.listaDeEstados) {
                for (int i = 0; i < estate.transicoes.size(); i++) {
                    Element transition = documentXML.createElement("transition");

                    Element from = documentXML.createElement("from");
                    from.appendChild(documentXML.createTextNode(Integer.toString(estate.transicoes.get(i).getIdOrigem())));
                    transition.appendChild(from);

                    Element to = documentXML.createElement("to");
                    to.appendChild(documentXML.createTextNode((estate.transicoes.get(i).getIdDestino())));
                    transition.appendChild(to);

                    Element read = documentXML.createElement("read");
                    read.appendChild(documentXML.createTextNode(String.valueOf(estate.transicoes.get(i).getSimbolo())));
                    transition.appendChild(read);

                    automaton.appendChild(transition);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

                //String current = new java.io.File(".").getCanonicalPath();

                DOMSource documentoFonte = new DOMSource(documentXML);
                //Caminha de onde será salvo o arquivo XML
                StreamResult documentoFinal = new StreamResult(new File(path));
                transformer.transform(documentoFonte, documentoFinal);

        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex);
        }
    }
}
