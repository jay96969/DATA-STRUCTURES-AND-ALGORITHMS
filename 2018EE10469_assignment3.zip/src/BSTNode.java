public class BSTNode<K,T> {

        K _key;
        T _value;
        BSTNode<K,T> _left, _right, _parent;

        public BSTNode(K key, T value){
            if(key == null){
                throw new IllegalArgumentException("key");
            }

            this._key = key;
            this._value = value;
        }

        public K key(){
            return this._key;
        }

        public T value(){
            return this._value;
        }

        public BSTNode<K,T> left(){
            return this._left;
        }

        public BSTNode<K,T> right(){
            return this._right;
        }

        public BSTNode<K,T> parent(){
            return _parent;
        }

        boolean isLeft(){
            if(_parent == null) return false;

            return _parent._left == this;
        }

        boolean isRight(){
            if(_parent == null) return false;

            return _parent._right == this;
        }

    
    }