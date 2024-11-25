package org.example;

public class Category {

        String name;
        String image;

        public Category() {
            DBConextion NewConextion = new DBConextion();
            NewConextion.StablishConection();
        }

        public Category(String name, String image) {
            DBConextion NewConextion = new DBConextion();
            NewConextion.StablishConection();
            this.name = name;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void SelectCategory() {
        }
        public void EditCategory() {
        }
        public void DeleteCategory() {
        }
        public void CreateCategory() {
        }

}
