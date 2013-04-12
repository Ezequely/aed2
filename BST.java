/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtree;

/**
 *
 * @author Teixeira
 */
public class BST {
    protected Nodo root;
    
    public BST(){
        root = null;
    }
    
    public void percursoEmOrdem(){
        inOrdem(root);
        System.out.println();
    }
    
    public void percursoPreOrdem(){
        preOrdem(root);
        System.out.println();
        
    }
    
    public void percursoPosOrdem(){
        posOrdem(root);
        System.out.println();
    }
    
    public Nodo procurarChave(int key){
        Nodo nodoAux = root;
        
        if(nodoAux!= null){
            while(nodoAux != null && nodoAux.getKey() != key){
                if(key < nodoAux.getKey()){
                    nodoAux = nodoAux.getLeft();
                }
                else{
                    nodoAux = nodoAux.getRight();
                }
            }
        }
        return nodoAux;
    }
    
    public Nodo minimo(Nodo nodo){
        if(nodo != null){
            if(nodo.getLeft() == null){
                return nodo;
            }
            else{
                return minimo(nodo.getLeft());
            }
        }
        else{
            return null;
        }
    }
    
    public Nodo maximo(Nodo nodo){
        if(nodo != null){
            if(nodo.getRight() == null){
                return nodo;
            }
            else{
                return maximo(nodo.getRight());
            }
        }
        else{
            return null;
        }
    }
    
    public Nodo predecessor(Nodo nodo){
        if(nodo.getLeft() == null){
            return maximo(nodo.getLeft());
        }
        else{
            Nodo nodoAux = pai(root, nodo.getKey());
            while(nodoAux != null && nodoAux.getLeft() == nodo){
                nodo = nodoAux;
                nodoAux = pai(root, nodoAux.getKey());
            }
            return nodoAux;
        }
    }
    
    public Nodo sucessor(Nodo nodo){
        if(nodo.getRight() != null){
            return minimo(nodo.getRight());
        }
        else{
            Nodo nodoAux = pai(root, nodo.getKey());
            while(nodoAux != null && nodoAux.getRight() == nodo){
                nodo = nodoAux;
                nodoAux = pai(root, nodoAux.getKey());
            }
            return nodoAux;
        }
    }
    
    public void inserir(int key){
        Nodo nodoAnt = null;
        Nodo nodoPos = root;
        Nodo novo = null;
        
        while(nodoPos != null && nodoPos.getKey() != key){
            nodoAnt = nodoPos;
            if(key < nodoPos.getKey()){
                nodoPos = nodoPos.getLeft();
            }
            else{
                nodoPos = nodoPos.getRight();
            }
        }
        if(nodoPos == null){
            novo = new Nodo(key);
            if(nodoAnt != null){
                if(key < nodoAnt.getKey()){
                    nodoAnt.setLeft(novo);
                }
                else{
                    nodoAnt.setRight(novo);
                }
            }
            else{
                root = novo;
            }
        }
    }
    
    public void remover(Nodo nodo){
       Nodo raiz = root;
       Nodo father = pai(root, nodo.getKey());
       
       while(nodo.getKey() != raiz.getKey()){
           if(nodo.getKey() < raiz.getKey()){
               raiz = raiz.getLeft();
           }
           else{
               raiz = raiz.getRight();
           }
       }
       if(raiz != null){
           if(raiz.getLeft() != null && raiz.getRight() != null){
               Nodo nodoAux = minimo(raiz.getRight());
               Nodo fatherAux = pai(root, nodoAux.getKey());
               if(raiz != fatherAux){
                   if(father != null){
                         nodoAux.setLeft(raiz.getLeft());
                         fatherAux.setLeft(nodoAux.getRight());
                         nodoAux.setRight(raiz.getRight());
                         if(raiz.getKey() < father.getKey()){
                             father.setLeft(nodoAux);
                         }
                         else{
                             father.setRight(nodoAux);
                         }
                   }
                   else{
                       fatherAux.setLeft(nodoAux.getRight());
                       nodoAux.setLeft(root.getLeft());
                       nodoAux.setRight(root.getRight());
                       root = nodoAux;
                   }
               }
               else{
                   nodoAux.setLeft(raiz.getLeft());
                   if(father != null){
                       if(raiz.getKey() < father.getKey()){
                           father.setLeft(nodoAux);
                       }
                       else{
                           father.setRight(nodoAux);
                       }
                   }
                   else{
                       root= nodoAux;
                   }
               }
           }
           else if(raiz.getLeft() != null){
               if(father != null){
                    if(raiz.getKey() < father.getKey()){
                        father.setLeft(raiz.getLeft());
                    }
                    else{
                        father.setRight(raiz.getLeft());
                    }
               }
               else{
                   root = root.getLeft();
               }
           }
           else{
               if(father != null){
                    if(raiz.getKey() < father.getKey()){
                        father.setLeft(raiz.getRight());
                    }
                    else{
                        father.setRight(raiz.getRight());
                    }
                }
               else{
                   root = root.getRight();
               }
           }
       }
    }
 
    public void print(){
        
    }
    
    public boolean arvoreBinariaBuscaValida(){
        return arvoreBinariaBuscaValidaAux(root);
    }
    
    protected Nodo pai(Nodo nodoRoot, int key){  
        if(nodoRoot == null){
            return null;
        }
        else{
            Nodo nodoLeft = nodoRoot.getLeft();
            Nodo nodoRight = nodoRoot.getRight();
           if((nodoLeft != null && key == nodoLeft.getKey()) || (nodoRight != null && key == nodoRight.getKey())){
               return nodoRoot;
           }
           else if(key < nodoRoot.getKey()){
               return pai(nodoRoot.getLeft(), key);
           }
           else{
               return pai(nodoRoot.getRight(), key);
           }
        }
    }
    
    protected void inOrdem(Nodo nodo){
        if(nodo != null){
            inOrdem(nodo.getLeft());
            System.out.print(nodo.getKey() + " ");
            inOrdem(nodo.getRight());
        }
    }
    
    protected void preOrdem(Nodo nodo){
        if(nodo != null){
            System.out.print(nodo.getKey() + " ");
            preOrdem(nodo.getLeft());
            preOrdem(nodo.getRight());
        }
    }
    
    protected void posOrdem(Nodo nodo){
        if(nodo != null){
            posOrdem(nodo.getLeft());
            posOrdem(nodo.getRight());
            System.out.print(nodo.getKey() + " ");
        }
    }
    
    protected boolean arvoreBinariaBuscaValidaAux(Nodo nodo){
        if(nodo != null){
            Nodo left = nodo.getLeft();
            Nodo right = nodo.getRight();
            if((left != null && left.getKey() > nodo.getKey()) || (right != null && right.getKey() < nodo.getKey())){
                return false;
            }
            else{
                return (true && arvoreBinariaBuscaValidaAux(left) && arvoreBinariaBuscaValidaAux(right));
            }
        }
        else{
            return true;
        }
    }
}
