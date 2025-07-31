package loja.api.enums;

public enum Exceptionmessage {
        EXISTING_PRODUCT_NAME("Já existe um produto com o nome: "),
        ID_NON_EXISTENT("Não existe nenhum produto com id: "),
        NONE_PROD_CAD("Nenhum produto cadastrado"),
        NONE_PROD_CAT("Nenhum produto possui essa categoria: "),
        CAMP_NON_EXISTENT("Campo inválido: ");

        private final String message;
        Exceptionmessage(String message) {this.message = message;}
        public String getMessage() {return this.message;}

}
