package loja.api.enums;

public enum Exceptionmessage {
        ID_NON_EXISTENT("Não existe nenhum produto com id: "),
        NONE_PROD_CAD("Nenhum produto cadastrado no banco de dados"),
        NONE_PROD_CAT("Nenhum produto possui essa categoria: "),
        CAMP_NON_EXISTENT("Campo inválido: ");

        private final String message;
        Exceptionmessage(String message) {this.message = message;}
        public String getMessage() {return this.message;}

}
