package Common.Commands;

public abstract class Command {
    public static String k_Separator = "::";

    public static String CommandCat(String str1, String str2){
        return str1 + k_Separator + str2;
    }

    private String tag;
    private String payload;

    public Command(String tag, String payload) {
        this.tag = tag;
        this.payload = payload;
    }

    public String getTag() {
        return tag;
    }

    public String getPayload() {
        return payload;
    }

    public String toString(){
        return CommandCat(tag, payload);
    }
}
