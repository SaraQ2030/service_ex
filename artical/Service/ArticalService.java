package org.example.artical.Service;

import org.example.artical.Model.Artical;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ArticalService {
    ArrayList<Artical> articals=new ArrayList<>();

    public ArrayList<Artical> getArticals(){
        return articals;
    }
    public void addArtical(Artical artical){
        articals.add(artical);
    }

    public boolean updateArtical(int id, Artical artical){
      for (int i=0;i< articals.size();i++){
            if (articals.get(i).getId()==id){
                articals.set(i, artical);
                return true;
            }
        }
        return false;
    }

    public boolean deleteArtical(int id){
        for (Artical a:articals){
            if (a.getId()==id){
                articals.remove(a);
                return true;
            }
        }
        return false;
    }

    public Artical publishArt(int id){
        for (Artical a:articals){
            if (a.getId()==id){
                if (!(a.isPublished())){
                    a.setPublished(true);
                    a.setPublishDate(LocalDate.now());
                    return a;
                }
            }
            }
        return null;
        }

        public ArrayList<Artical> allPublish(){
        ArrayList<Artical> allPub=new ArrayList<>();
        for (Artical a:articals){
            if (a.isPublished()){
                allPub.add(a);
            }
        }
        return allPub;
        }

        public ArrayList<Artical> searchCategory(String cate){
        ArrayList<Artical> catArt=new ArrayList<>();
        for (Artical a:articals){
            if (a.getCategory().equalsIgnoreCase(cate)){
                catArt.add(a);

            }
        }
return catArt;
        }


    }




