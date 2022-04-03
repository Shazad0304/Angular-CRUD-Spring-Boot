import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../Product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  product: Product = new Product();
  
  constructor(private productService: ProductService,
    private router: Router) { }

  ngOnInit() {
  }

  newStudent(): void {
    
    this.product = new Product();
  }
 
  save() {
    this.productService.createProduct(this.product)
      .subscribe(
        data => {
          this.router.navigate(['product']);
          console.log(data);
          
        },
        error => console.log(error));
    this.product = new Product();
  }
 
  onSubmit() {
    this.save();
  }

  

}
