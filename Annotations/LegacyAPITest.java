package Annotations_Reflection;

class LegacyAPI {

    @Deprecated
    public void oldFeature() {
        System.out.println("This is the old feature. It should not be used.");
    }

    public void newFeature() {
        System.out.println("This is the new and recommended feature.");
    }
}

public class LegacyAPITest {
    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature();  //warning
        api.newFeature();
    }
}

