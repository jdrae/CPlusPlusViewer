class Stack{
public:
	Stack(int s=10);
	~Stack(){delete[] ptr;}
	void push(int v);
	int pop();
protected:
	bool isEmpty();
	bool isFull();
private:
	int size;
	int top;
	int *ptr;
};

Stack::Stack(int s){
	size = s > 0 ? s : 10
	top = -1;
	ptr = new int(size);
}

void Stack::push(int v){
	if(!isFull()){
		ptr(++top) =v;
	}
}

int Stack::pop(){
	if(!isEmpty()){
		return ptr(top--);
	}
	exit(1);
}

bool Stack::isFull(){
	if(top>=size-1) return true;
	else return false;
}

bool Stack::isEmpty(){
	if(top==-1) return true;
	else return false;
}


