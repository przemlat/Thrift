namespace java edu.pjwstk.sri.lab08

struct Product {
	1:i64 id,
	2:string name,
	3:double price,
	4:i32 numberOfPieces
}

struct OrderItem {
	1:i64 productId,
	2:i32 numberOfPieces
}

service ProductListService {
	list<Product> getProductsList()
}

exception ProductNotAvailableException {
	1: string msg
}

service ProductCart {
    void addItem(1:OrderItem orderItem) throws (1: ProductNotAvailableException productNotAvailableException),
    void removeFromCart(1:i64 productId),
    void changeNumberOfPieces(1:OrderItem orderItem) throws (1: ProductNotAvailableException productNotAvailableException),
    string confirmOrder()
}

