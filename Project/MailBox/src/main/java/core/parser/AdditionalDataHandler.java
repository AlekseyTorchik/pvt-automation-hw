package core.parser;

import model.AdditionalData;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AdditionalDataHandler extends DefaultHandler {
    private List<AdditionalData> fields;
    private AdditionalData additionalData;
    boolean isContactMail = false;
    boolean isSubjectOfLetter = false;
    boolean isMessageInLetter = false;
    boolean isApiKey = false;
    boolean isNameOfNewFolder = false;
    boolean isMethodNameFirstStep = false;
    boolean isMethodNameSecondStep = false;
    boolean isMethodNameThirdStep = false;
    boolean isAccessToken = false;
    boolean isOwnerIdNumber = false;
    boolean isMessageCreate = false;
    boolean isMessageEdit = false;
    boolean isVersionAPI = false;

    public List<AdditionalData> getFields() {
        return fields;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("AdditionalData")) {
            String id = attributes.getValue("id");
            additionalData = new AdditionalData();
            additionalData.setId(Integer.parseInt(id));
            if (fields == null) {
                fields = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("contactMail")) {
            isContactMail = true;
        } else if (qName.equalsIgnoreCase("subjectOfLetter")) {
            isSubjectOfLetter = true;
        } else if (qName.equalsIgnoreCase("messageInLetter")) {
            isMessageInLetter = true;
        } else if (qName.equalsIgnoreCase("apiKey")) {
            isApiKey = true;
        } else if (qName.equalsIgnoreCase("nameOfNewFolder")) {
            isNameOfNewFolder = true;
        } else if (qName.equalsIgnoreCase("methodNameFirstStep")) {
            isMethodNameFirstStep = true;
        } else if (qName.equalsIgnoreCase("methodNameSecondStep")) {
            isMethodNameSecondStep = true;
        } else if (qName.equalsIgnoreCase("methodNameThirdStep")) {
            isMethodNameThirdStep = true;
        } else if (qName.equalsIgnoreCase("accessToken")) {
            isAccessToken = true;
        } else if (qName.equalsIgnoreCase("ownerIdNumber")) {
            isOwnerIdNumber = true;
        } else if (qName.equalsIgnoreCase("messageCreate")) {
            isMessageCreate = true;
        } else if (qName.equalsIgnoreCase("versionAPI")) {
            isMessageEdit = true;
        } else if (qName.equalsIgnoreCase("messageEdit")) {
            isVersionAPI = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("AdditionalData")) {
            fields.add(additionalData);
        }
    }

    @Override
    public void characters(char characters[], int start, int length) {

        if (isContactMail) {
            additionalData.setContactMail(new String(characters, start, length));
            isContactMail = false;
        } else if (isSubjectOfLetter) {
            additionalData.setSubjectOfLetter(new String(characters, start, length));
            isSubjectOfLetter = false;
        } else if (isMessageInLetter) {
            additionalData.setMessageInLetter(new String(characters, start, length));
            isMessageInLetter = false;
        } else if (isApiKey) {
            additionalData.setApiKey(new String(characters, start, length));
            isApiKey = false;
        } else if (isNameOfNewFolder) {
            additionalData.setNameOfNewFolder(new String(characters, start, length));
            isNameOfNewFolder = false;
        } else if (isMethodNameFirstStep) {
            additionalData.setMethodNameFirstStep(new String(characters, start, length));
            isMethodNameFirstStep = false;
        } else if (isMethodNameSecondStep) {
            additionalData.setMethodNameSecondStep(new String(characters, start, length));
            isMethodNameSecondStep = false;
        } else if (isMethodNameThirdStep) {
            additionalData.setMethodNameThirdStep(new String(characters, start, length));
            isMethodNameThirdStep = false;
        } else if (isAccessToken) {
            additionalData.setAccessToken(new String(characters, start, length));
            isAccessToken = false;
        } else if (isOwnerIdNumber) {
            additionalData.setOwnerIdNumber(new String(characters, start, length));
            isOwnerIdNumber = false;
        } else if (isMessageCreate) {
            additionalData.setMessageCreate(new String(characters, start, length));
            isMessageCreate = false;
        } else if (isMessageEdit) {
            additionalData.setMessageEdit(new String(characters, start, length));
            isMessageEdit = false;
        } else if (isVersionAPI) {
            additionalData.setVersionAPI(new String(characters, start, length));
            isVersionAPI = false;
        }
    }
}