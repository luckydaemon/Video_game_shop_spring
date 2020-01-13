Vue.component('tabs', {
    template: `
        <div>
            <div class="tabs">
              <ul>
                <li v-for="tab in tabs" :class="{ 'is-active': tab.isActive }">
                    <a :href="tab.href" @click="selectTab(tab)">{{ tab.name }}</a>
                </li>
              </ul>
            </div>

            <div class="tabs-details">
                <slot></slot>
            </div>
        </div>
    `,

    data() {
        return {tabs: [] };
    },

    created() {

        this.tabs = this.$children;

    },
    methods: {
        selectTab(selectedTab) {
            this.tabs.forEach(tab => {
                tab.isActive = (tab.name == selectedTab.name);
            });
        }
    }
});

Vue.component('tab', {

    template: `

        <div v-show="isActive"><slot></slot></div>

    `,

    props: {
        name: { required: true },
        selected: { default: false}
    },

    data() {

        return {
            isActive: false
        };

    },

    computed: {

        href() {
            return '#' + this.name.toLowerCase().replace(/ /g, '-');
        }
    },

    mounted() {

        this.isActive = this.selected;

    }
});

Vue.component('ClientName', {
    props:[ ],
    data: function() {return {
        results: ''
        }
        },
    created: function () {
            axios.get("http://localhost:3333/shop/currentclient").then((response) => {
                                                                                         console.log(response.data.firstName);
                                                                                         this.results = response.data.firstName
                                                                                         })
                  },
    template:'<div> hello   {{results}} </div>'
});

Vue.component('ProductsTable', {
    props: [],

    template: `<div>
                   <b-table
                     ref="selectableTable"
                     selectable
                     :select-mode='multi'
                     :items="items"
                     :fields="fields"
                     @row-selected="onRowSelected"

                     responsive="sm"
                   >
                     <!-- Example scoped slot for select state illustrative purposes -->
                     <template v-slot:cell(selected)="{ rowSelected }">
                       <template v-if="rowSelected">
                         <span aria-hidden="true">&check;</span>
                         <span class="sr-only">Selected</span>
                       </template>
                       <template v-else>
                         <span aria-hidden="true">&nbsp;</span>
                         <span class="sr-only">Not selected</span>
                       </template>
                     </template>
                   </b-table>
                   <p>
                     <b-button size="sm" @click="clearSelected">Clear selected</b-button>
                     <b-button size="sm" @click="createOrder">Create Order</b-button>
                   </p>
                   <p>
                     Your order:<br>
                     {{ selected }}
                   </p>
                 </div>
            `,
   data() {
         return {
           fields: ['productID','productName', 'productPrice', 'category', 'productQuantity'],
           items: [
           ],
           selected: [],
           clientId: 0,
           orderCost: 0,
           orderId: ''
         }
       },
       computed: {
                orderCost: function(){
                                                let sum = 0;
                                               for(let i = 0; i < this.selected.length; i++){
                                                       console.log(this.selected[i].productPrice)
                                                       sum += (parseFloat(this.selected[i].productPrice));
                                                     }
                                               return sum;
                                                               }
           },
       methods: {
         onRowSelected(items) {
           this.selected = items
         },
         createOrder(items) {
             var id = 0
            axios.get("http://localhost:3333/shop/currentclient").then((response) => {

                                                                                                     this.clientId = response.data.id
                                                                                                     })

               let sum = 0;
              for(let i = 0; i < this.selected.length; i++){
                sum += (parseFloat(this.selected[i].productPrice))
              }
              this.orderCost = sum


               var productlist = []
               var productsID = []
               let product = {productID : 0, productName : 0,  productPrice : 0, category : 0, productQuantity : 0}
               for(let i = 0; i < this.selected.length; i++){
                                Object.assign(product, {productID : this.selected[i].productID,productName : this.selected[i].productName,
                                 productPrice : this.selected[i].productPrice, category : this.selected[i].category,
                                 productQuantity : this.selected[i].productQuantity})
                               productlist.push(product)
                                productsID.push(this.selected[i].productID)
                               product = {productID : 0, productName : 0,  productPrice : 0, category : 0, productQuantity : 0}
                             }

               axios({
                     method: 'post',
                     url: 'http://localhost:3333/shop/neworder/',
                     data: {
                       client_id :  this.clientId,
                       cost : this.orderCost,
                       list: productsID
                     }
                   });
               productlist = []
               productsID = []
          },
         clearSelected() {
           this.$refs.selectableTable.clearSelected()
         }
       },
    created: function () {
         axios.get("http://localhost:3333/getAllProducts/").then((response) => {

                                                                                                 this.items = response.data
                                                                                                 })
          axios.get("http://localhost:3333/shop/currentclient").then((response) => {

                                                                                                              this.clientId = response.data.id
                                                                                                              })
    }
});


Vue.component('OrdersTable', {
    props: [],

    template: `<div>
                   <b-table
                     ref="selectableTable"
                     selectable
                     :select-mode='none'
                     :items="items"
                     :fields="fields"
                     @row-selected="onRowSelected"

                     responsive="sm"
                   >
                     <!-- Example scoped slot for select state illustrative purposes -->
                     <template v-slot:cell(selected)="{ rowSelected }">
                       <template v-if="rowSelected">
                         <span aria-hidden="true">&check;</span>
                         <span class="sr-only">Selected</span>
                       </template>
                       <template v-else>
                         <span aria-hidden="true">&nbsp;</span>
                         <span class="sr-only">Not selected</span>
                       </template>
                     </template>
                   </b-table>
                   <p>

                   </p>
                 </div>
            `,
   data() {
         return {
           fields: ['orderId','products', 'cost', 'status'],
           items: [
           ],
           selected: [],
           clientId: ''
         }
       },
       methods: {
         onRowSelected(items) {
           this.selected = items
         },
         async getUser (){
            var self = this
                     await axios.get("http://localhost:3333/shop/currentclient").then((response) => {
                                                                                                console.log(response.data.id)
                                                                                                 self.clientId = response.data.id
                                                                                                                           })
                      console.log(self.clientId)




         },
          async getItems (){
                            var self = this
                            var Orders= []
                               console.log("http://localhost:3333/getOrdersByClient/"+self.clientId)
                              await   axios.get("http://localhost:3333/shop/getOrdersByClient/"+self.clientId).then((response) => {
                                                                                                   console.log(response.data)
                                                                                                   Orders = response.data})
                               console.log(Orders)
                               let Order = {orderId : 0, products : [], cost : 0, status : ''}
                              for(let i = 0; i < Orders.length; i++){
                                   var productInOrder = []
                                   for   (let j = 0; j < Orders[i].ordersProducts.length; j++)
                                   {
                                        console.log(Orders[i].ordersProducts[j])
                                        console.log(Orders[i].ordersProducts[j].product.productName)
                                        productInOrder.push(Orders[i].ordersProducts[j].product.productName)
                                   }
                                   Object.assign(Order,{orderId : Orders[i].orderId, products : productInOrder, cost : Orders[i].cost, status : Orders[i].status})
                                   self.items.push(Order)
                                   Order = {orderId : 0, products : [], cost : 0, status : ''}
                                   productInOrder = []
                              }
                             console.log(self.items)
          },
          async create()
          {
                          await this.getUser ()
                               console.log("http://localhost:3333/getOrdersByClient/"+this.clientId)
                          await this.getItems()
                            console.log(this.Items)
          }
       },
      created : function () {
                this.create()
     }

});

Vue.component('messages-list', {
    props: [],
    template: `
            <div id="app" class="container">
            <ClientName> </ClientName>
            <tabs>
                <tab name="Create order" :selected="true">
                  <h1>Here you can create order</h1>
                   <ProductsTable> </ProductsTable>
                </tab>
                <tab name="Your orders">
                  <h1> Your order history</h1>
                  <OrdersTable> </OrdersTable>
                </tab>
              </tabs>
            </div>`,
});

var app = new Vue({
       el: '#app',
       template: '<messages-list />',
       data: {
           messages: [ ]
       }
   });