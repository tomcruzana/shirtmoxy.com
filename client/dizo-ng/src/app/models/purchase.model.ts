import { Address } from "./address.model";
import { Customer } from "./customer.model";
import { OrderItem } from "./order-item.model";
import { Order } from "./order.model";

export class Purchase {
    public customer: Customer;
    public shippingAddress: Address;
    public billingAddress: Address;
    public order: Order;
    public orderItems: OrderItem[];

    constructor(
        customer?: Customer,
        shippingAddress?: Address,
        billingAddress?: Address,
        order?: Order,
        orderItems?: OrderItem[]
    ) {
        this.customer = customer || new Customer();
        this.shippingAddress = shippingAddress || new Address();
        this.billingAddress = billingAddress || new Address();
        this.order = order || new Order();
        this.orderItems = orderItems || [];
    }
}
