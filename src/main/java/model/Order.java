package model;

import services.ProductService;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private ArrayList<Product> lista=new ArrayList<Product>();
    private User user;
    private int contor=0;

    public boolean exista(Product produs){
        boolean temp2=false;
        for (int i=0;i<contor;i++)
            if(produs.getName().equals(lista.get(i).getName()))
                temp2=true;
        return temp2;
    }



    public void addProduct(Product product){
        boolean temp1=false;
        Integer temp3=0;
        for (Product k : ProductService.productRepository.find())
            if (Objects.equals(product.getName(), k.getName()))
                temp1=true;
        for (Product k : ProductService.productRepository.find())
            if (Objects.equals(product.getName(), k.getName()))
                temp3=k.getQuantity();

            if(exista(product)) {
                for (int i = 0; i < contor; i++)
                    if (product.getName().equals(lista.get(i).getName()))
                        if(lista.get(i).getQuantity() + product.getQuantity()<=temp3)
                            lista.get(i).setQuantity(lista.get(i).getQuantity() + product.getQuantity());
            }else
        if (temp1)
            if(product.getQuantity()<temp3){
                lista.add(product);
        contor++;}
    }

    public void removeProduct(Product product){
        for (int i=0;i<contor;i++)
            if(product.equals(lista.get(i).getName()))
                lista.remove(i);
        //lista.add(product);
        contor--;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public ArrayList<Product> getOrder() { return lista; }

    public int getContor() { return contor; }
}
