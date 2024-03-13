package org.example.artical.Controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.artical.Api.ApiMessage;
import org.example.artical.Model.Artical;
import org.example.artical.Service.ArticalService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/artical")
@RequiredArgsConstructor
public class ArticalController {
    private final ArticalService articalService;

    @GetMapping("/get")
    public ResponseEntity getArtical(){
        ArrayList<Artical> articals=articalService.getArticals();
        return ResponseEntity.status(200).body(articals);
    }

    @PostMapping("/add")
    public ResponseEntity addArtical(@RequestBody @Valid  Artical artical, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        articalService.addArtical(artical);
        return ResponseEntity.status(200).body(new ApiMessage("Artical added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArtical(@PathVariable int id ,@RequestBody @Valid Artical artical,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=articalService.updateArtical(id,artical);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiMessage("Data updated"));
        }
        return ResponseEntity.status(400).body("Not found id");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArtical(@PathVariable int id){
boolean isDeleted=articalService.deleteArtical(id);
if (isDeleted){
    return ResponseEntity.status(200).body(new ApiMessage("Artical deleted"));
}
        return ResponseEntity.status(400).body(new ApiMessage("Id not found"));

    }

    @PutMapping("pub/{id}")
    public ResponseEntity publishArt(@PathVariable int id){
        Artical b=articalService.publishArt(id);
        if (b==null){
            return ResponseEntity.status(400).body(new ApiMessage("Id not found "));
        }
        return ResponseEntity.status(200).body(b);
    }
    @GetMapping("/search/all-pub")
    public ResponseEntity allpublish(){
        ArrayList<Artical> all=articalService.allPublish();
        return ResponseEntity.status(200).body(all);
    }

@GetMapping("/search/cat/{c}")
    public ResponseEntity searchCategory(@PathVariable String c){
        ArrayList<Artical> list=articalService.searchCategory(c);
        if (list.isEmpty()){
            return ResponseEntity.status(400).body(new ApiMessage("Not found "));
        }
        return ResponseEntity.status(200).body(list);

}
}
