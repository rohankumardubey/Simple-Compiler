package cop5556fa17;

import java.util.HashMap;

import cop5556fa17.AST.ASTNode;
import cop5556fa17.AST.Declaration;

public class SymbolTable {
    HashMap<String, Declaration> hm;
    SymbolTable(){
        hm = new HashMap<String, Declaration>();
    }
    void insert(String key,Declaration node){
        hm.put(key, node);
    }
    boolean iscontains(String key){
        return hm.containsKey(key);
    }
    Declaration getfromSymboltable(String key){
        return hm.get(key);
    }
    

}
