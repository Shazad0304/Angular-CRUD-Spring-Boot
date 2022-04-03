import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProdListComponent } from './prod-list/prod-list.component';
import { AddComponent } from './add/add.component';
import { UpdateComponent } from './update/update.component';
import { AuthComponent } from './auth/auth.component';
import { AuthGuard } from './auth/auth.guard';


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {path: 'login', component:AuthComponent},
  { path: 'product', component: ProdListComponent,canActivate:[AuthGuard] },
  { path: 'add', component:  AddComponent ,canActivate:[AuthGuard] },
  { path: 'update/:id', component: UpdateComponent ,canActivate:[AuthGuard] },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
