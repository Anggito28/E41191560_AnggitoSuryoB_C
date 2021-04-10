<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\DashboardController;
use App\Http\Controllers\Frontend\HomeController;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/
//Route::get('user', 'ManagementUserController@index');
//Route::resource('user', 'ManagementUserController');


Route::get('/', function () {
    return view('welcome');
});
Route::get("/home", function () {
    return view("home", ["nama" => "Anggito Suryo B", "pelajaran" => ["Algoritma & Pemrograman", "Kalkulus", "Pemrograman Web"]]);
});
Route::group(['namespace' => 'Frontend'], function () {
    Route::resource('home', 'HomeController');
});
Route::group(['namespace' => 'Backend'], function () {
    Route::resource('dashboard', 'DashboardController');
});
