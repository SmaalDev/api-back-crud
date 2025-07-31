package loja.api.enums;

public enum CategoryExceptionMessage {

        EXISTING_CATEGORY("Já existe uma categoria com o nome: "),
        ID_NON_EXISTENT("Não existe nenhuma categoria com ID: "),
        NONE_PROD_CAD("Nenhuma categoria cadastrada");

        private final String message;
        CategoryExceptionMessage(String message) {this.message = message;}
        public String getMessage() {return this.message;}
}
