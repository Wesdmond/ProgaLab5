package Model;

import Model.DataClasses.*;
import Model.Exceptions.BadIdException;
import Model.Exceptions.LoadFailedException;
import Model.Exceptions.SaveFailedException;
import Model.Exceptions.UserInputException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class XmlModel implements IModel{

    private String file_path = null;
    DocumentBuilderFactory factory;

    public XmlModel() {
        factory = DocumentBuilderFactory.newInstance();
    }

    public XmlModel(String file_path) {
        this();
        this.file_path = file_path;
    }

    public void setFilePath(String new_path) {
        file_path = new_path;
    }

    @Override
    public String saveData(Dragon[] data) throws SaveFailedException {
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("root");
            document.appendChild(root);

            for (Dragon dragon : data) {
                Element drag = document.createElement("Dragon");

                Element id = document.createElement("id");
                Text textId = document.createTextNode(dragon.getId().toString());
                id.appendChild(textId);

                Element name = document.createElement("name");
                Text textName = document.createTextNode(dragon.getName());
                name.appendChild(textName);

                Element coordinates = document.createElement("coordinates");
                Element x = document.createElement("x");
                Element y = document.createElement("y");
                Text textX = document.createTextNode(((Integer)dragon.getCoordinates().getX()).toString());
                x.appendChild(textX);
                Text textY = document.createTextNode((dragon.getCoordinates().getY()).toString());
                y.appendChild(textY);
                coordinates.appendChild(x);
                coordinates.appendChild(y);

                Element age = document.createElement("age");
                Text textAge = document.createTextNode(dragon.getAge().toString());
                age.appendChild(textAge);

                Element color = document.createElement("color");
                Text textColor = document.createTextNode(dragon.getColor().toString());
                color.appendChild(textColor);

                Element dragonType = document.createElement("dragonType");
                Text textType = document.createTextNode(dragon.getType().toString());
                dragonType.appendChild(textType);

                Element dragonCharacter = document.createElement("dragonCharacter");
                Text textCharacter = document.createTextNode(dragon.getCharacter().toString());
                dragonCharacter.appendChild(textCharacter);

                Element dragonHead = document.createElement("dragonHead");
                Element eyes = document.createElement("eyes");
                Text textEyes = document.createTextNode(((Integer)dragon.getHead().getEyesCount()).toString());
                eyes.appendChild(textEyes);
                dragonHead.appendChild(eyes);

                Element creationDate = document.createElement("creationDate");
                Text textDate = document.createTextNode(IModel.getDefaultDateFormat().format(dragon.getCreationDate()));
                creationDate.appendChild(textDate);

                drag.appendChild(id);
                drag.appendChild(name);
                drag.appendChild(coordinates);
                drag.appendChild(age);
                drag.appendChild(color);
                drag.appendChild(dragonType);
                drag.appendChild(dragonCharacter);
                drag.appendChild(dragonHead);
                drag.appendChild(creationDate);

                root.appendChild(drag);
                // Возможно данные не сохраняются и потребуется фикс
            }
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(document), new StreamResult(new PrintWriter(getFilePath())));


        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            throw new SaveFailedException("");
        } catch (FileNotFoundException | TransformerException e) {
            throw new SaveFailedException(String.format("Невозможно получить доступ к файлу %s", getFilePath()));
        }
        return getFilePath();
    }

    @Override
    public Dragon[] loadData() throws LoadFailedException {
        Dragon[] dragons;
        try {
            Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(readXml().getBytes()));
            int dragonsSize = doc.getElementsByTagName("Dragon").getLength();
            dragons = new Dragon[dragonsSize];

            for (int dragonInd = 0; dragonInd < dragonsSize; ++dragonInd) {
                Integer id = Integer.parseInt(doc.getElementsByTagName("id").item(dragonInd).getTextContent());
                String name = doc.getElementsByTagName("name").item(dragonInd).getTextContent();
                int x = Integer.parseInt(doc.getElementsByTagName("x").item(dragonInd).getTextContent());
                Long y = Long.parseLong(doc.getElementsByTagName("y").item(dragonInd).getTextContent());
                Long age = Long.parseLong(doc.getElementsByTagName("age").item(dragonInd).getTextContent());
                String color = doc.getElementsByTagName("color").item(dragonInd).getTextContent();
                String type = doc.getElementsByTagName("dragonType").item(dragonInd).getTextContent();
                String character = doc.getElementsByTagName("dragonCharacter").item(dragonInd).getTextContent();
                int eyes = Integer.parseInt(doc.getElementsByTagName("eyes").item(dragonInd).getTextContent());
                Date date = IModel.getDefaultDateFormat().parse(doc.getElementsByTagName("creationDate").item(dragonInd).getTextContent());

                dragons[dragonInd] = new Dragon(id, name, new Coordinates(x, y), age, Color.valueOf(color), DragonType.valueOf(type), DragonCharacter.valueOf(character), new DragonHead(eyes));
                dragons[dragonInd].setCreationDate(date);
            }
        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException | UserInputException | BadIdException |
                 ParseException e) {
            throw new LoadFailedException("Проблема при парсинге XML");
        }
        return dragons;
    }

    private String readXml() throws LoadFailedException {
        try {
            Scanner sc = new Scanner(new File(getFilePath()));
            String result = "";
            while (sc.hasNext()) {
                result += sc.next() + ' ';
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new LoadFailedException(String.format("Не удалось найти файл %s", getFilePath()));
        }
    }

    private String getFilePath() {
        String result = file_path;
        //Paths.get(file_path);

        if (result == null)
            result = Paths.get(System.getProperty("user.dir"), "dragon-data.xml").toAbsolutePath().toString();
        System.out.println(result);
        return result;
    }
}
