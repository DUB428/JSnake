package objects;

import java.util.ArrayList;

/**
 * Created by samuel on 14.05.15.
 */
public class LangStringGroup {

    String pnlName;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();

    public LangStringGroup (String pnlName) {
        this.pnlName = pnlName;
    }

    public void addString(String name, String content) {
        this.names.add(name);
        this.content.add(content);
    }

    public void changeStringContent(String name, String newContent) {
        //changes the content of a String
        int index = names.indexOf(name);
        this.content.set(index, newContent);
    }

    public void changeStringName(String newName, String oldName) {
        //changes the name of a String based on the old name
        int index = names.indexOf(oldName);
        names.set(index, newName);
    }


    public String getString(String name) {
        //returns the content of the string which is asked for
        //if string does not exits, it returns the name of the nonexisting-String to be displayed
        if (names.indexOf(name) == -1) {
            //adds empty string, so that it won't be created twice and all strings are getting countable
            addString(name, "String not found: " + name);
            return "String not found: " + name;
        }
        //check if String does exist but is empty
        String content = this.content.get(names.indexOf(name));
        if(content.equals("")) {
            return "String empty: " + name;
        }
        return content;
    }

    public String getPanelName() {
        return pnlName;
    }

    public int getAmountStrings() {
        //returns amount of strings in this LangStringGroup
        return names.size();
    }

    public String[] getAllContent() {
        String[] allContent = new String[getAmountStrings()];
        for(int i = 0; i < getAmountStrings(); i++) {
            allContent[i] = content.get(i);
        }
        return allContent;
    }

    public String[] getAllNames() {
        String[] allNames = new String[getAmountStrings()];
        for(int i = 0; i < getAmountStrings(); i++) {
            allNames[i] = names.get(i);
        }
        return allNames;
    }

    public void checkForDoubles() {
        //checks for strings having the same name
        ArrayList<Integer> indexDoubles = new ArrayList<>();
        for(int i = 0; i < names.size(); i++) {
            for(int j = 0; j < i; j++) {
                if(names.get(i).equals(names.get(j))) {
                    indexDoubles.add(i);
                }
            }
            for(int j = i+1; j < names.size(); j++) {
                if(names.get(i).equals(names.get(j))) {
                    indexDoubles.add(i);
                }
            }
        }
        System.out.println(indexDoubles);
    }
}
