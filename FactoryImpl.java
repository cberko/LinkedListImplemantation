import java.util.NoSuchElementException;


public class FactoryImpl implements Factory{
	private Holder first;
	private Holder last;
	private Integer size = 0;

	

	@Override
	public void addFirst(Product product) {
		// TODO Auto-generated method stub
		Holder holder = new Holder(null, product, first);
		if (first == null) {
			last = holder;
			first = holder;
		} else {
			first.setPreviousHolder(holder);
			holder.setNextHolder(first);
			first = holder;
		}

		size+=1;
	
	}
	@Override
	public void addLast(Product product) {
		Holder holder = new Holder(null,product,null);
		if (size==0){
			last = holder;
			first = holder;
		}
		else if (size==1){
			holder.setPreviousHolder(first);
			first.setNextHolder(holder);
			last = holder;
		}
		else{
			holder.setPreviousHolder(last);
			last.setNextHolder(holder);
			last = holder;
		}
		size+=1;
		// TODO Auto-generated method stub
		
	}
	@Override
	public Product removeFirst() throws NoSuchElementException {
		Product result ;
		if (size==0){
			throw new NoSuchElementException("Factory is empty.");
			
		}
		else if(size==1){
			result=(first.getProduct());
			first.setProduct(null);
			last = null;
			first = null;
			size -=1;
			return result;

			}
		else{
			result=(first.getProduct());
			first = first.getNextHolder();
			first.setPreviousHolder(null);
			size -=1;
			return result;
			
			
		}

	}
	@Override
	public Product removeLast() throws NoSuchElementException {
		Product result ;
		if (size==0){
			throw new NoSuchElementException("Factory is empty.");
		}
		else if(size==1){
			result = last.getProduct();
			last.setProduct(null);
			last = null;
			first = null;
			size -=1;
			return result;
			}
		else{


			result = last.getProduct();

			last = last.getPreviousHolder();
			last.setNextHolder(null);
			size -=1;
			return 	result;
		}
		// TODO Auto-generated method stub

	}
	@Override
	public Product find(int id) throws NoSuchElementException {
		Holder traverse = first;
		Boolean flag = false;
		while (traverse!=null){
			if (traverse.getProduct().getId() == id){
				flag = true;
				break;
			}
			traverse = traverse.getNextHolder();
		}
		if (flag == false){
			throw new NoSuchElementException("Product not found.");
		}
		else {
			return traverse.getProduct();
		}
		
	}
		// TODO Auto-generated method stub}
	@Override
	public Product update(int id, Integer value) throws NoSuchElementException {
		// TODO Auto-generated method stub
		Holder traverse = first;
		Boolean flag = false;

		while (traverse!=null){
			if (traverse.getProduct().getId() == id){
				flag = true;
				break;
			}
			traverse = traverse.getNextHolder();

		}
		if (flag == false){
			throw new NoSuchElementException("Product not found.");
		}
		else {
			Product result = new Product(traverse.getProduct().getId(),traverse.getProduct().getValue());

			traverse.getProduct().setValue(value);


			return result;
		}

	}
	@Override
	public Product get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub


		if (index >= size || index < 0){
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		else{
			Holder traverse = first;
			for (int i = 0; i < index; i++){
				traverse = traverse.getNextHolder();

			}
			return traverse.getProduct();
		}
		
	}
	@Override
	public void add(int index, Product product) throws IndexOutOfBoundsException {


		if (index < 0 || index > size ){

			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		if (index == 0) {
			addFirst(product);
		}
		else if (index == size){
			addLast(product);
		}

		else {
			int counter = 0;
			Holder holder = new Holder(null,product,null);
			Holder traverse = first;
			while (counter < index) {
				traverse = traverse.getNextHolder();


				counter += 1;
			}
			holder.setPreviousHolder(traverse.getPreviousHolder());
			traverse.getPreviousHolder().setNextHolder(holder);

			holder.setNextHolder(traverse);

			traverse.setPreviousHolder(holder);




			size += 1;
		}



		
	}
	@Override
	public Product removeIndex(int index) throws IndexOutOfBoundsException {
		int counter = 0;
		Product result;
		Holder traverse = first;
		if (index<0 || index>=size){
			throw new IndexOutOfBoundsException("Index out of bounds.")	;	}
		while(counter<index){
			traverse = traverse.getNextHolder();
			counter +=1;
	}
	result = traverse.getProduct();
	if (index==0){
		return removeFirst();
	}
	if (index == size-1){
		return removeLast();
	}
		traverse.getPreviousHolder().setNextHolder(traverse.getNextHolder());


		traverse.getNextHolder().setPreviousHolder(traverse.getPreviousHolder());

	size -=1;

		// TODO Auto-generated method stub
		return result;
	}
	@Override
	public Product removeProduct(int value) throws NoSuchElementException {
		Product result = null;
		Holder traverse = first;
		Boolean flag = false;
		while(traverse!= null){
			if (traverse.getProduct().getValue()==value){
				result = traverse.getProduct();
				if (traverse!= first && traverse!=last) {
					result = traverse.getProduct();
					flag = true;
					traverse.getPreviousHolder().setNextHolder(traverse.getNextHolder());
					traverse.getNextHolder().setPreviousHolder(traverse.getPreviousHolder());
					break;
				}
				if(traverse==first){
					return removeFirst();

					}
				else if (traverse==last){
					return removeLast();
					}

					}
			traverse = traverse.getNextHolder();

		}

		if(flag==false){
			throw new NoSuchElementException("Product not found.");
		}
		else {
			size -= 1;
			return result;
		}
		// TODO Auto-generated method stub

	}
	@Override
	public int filterDuplicates(){

		int counter = 0;
		Holder traverse = first;
		if (traverse == null){
			return  0;
		}

		while (traverse != null){
			Holder traverse2 = traverse.getNextHolder();
			Integer val = traverse.getProduct().getValue();
			while (traverse2 != null){
				if (traverse2.getProduct().getValue() == val) {
					Holder next = traverse2.getNextHolder();
					Holder prev = traverse2.getPreviousHolder();
					prev.setNextHolder(next);
					if (next != null)
						next.setPreviousHolder(prev);
					else
						last= prev;
					size -=1;
					counter +=1;
				}
				traverse2 = traverse2.getNextHolder();
			}
			traverse = traverse.getNextHolder();
		}
		return counter;

	}
	@Override
	public void reverse() {

		if(size==1){
			return;
		}
		Holder traverse = first;
		last = first;
		Holder temp = null;
		while (traverse != null){
			temp = traverse.getPreviousHolder();
			traverse.setPreviousHolder(traverse.getNextHolder());
			traverse.setNextHolder(temp);
			traverse = traverse.getPreviousHolder();
		}
		if (temp!=null){
			first = temp.getPreviousHolder();
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		if (first==null){
			return "{}";
		}
		else{
			String result = "{";
			Holder traverse = first;
			while(traverse!=null){
				result = result + traverse.getProduct().toString();
				if (traverse.getNextHolder()!=null){
					result += ",";
				}
				traverse = traverse.getNextHolder();
			}
			result += "}";
			return result;
		
	}
	}
}
