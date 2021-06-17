<?php

use Illuminate\Support\Facades\Route;

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

// Route::get('/', function () {
//     return view('welcome');
// });

// Route::get('/dashboard', function () {
//     return view('dashboard');
// })->middleware(['auth'])->name('dashboard');

Route::middleware(['auth'])->group(function () {
    // Route::prefix('admin')->group(function () {

    // });
    Route::get('/', function () {
        return view('dashboard');
    });
    Route::get('dashboard', function() {
        return view('dashboard');
    });
});
Route::middleware(['auth'])->group(function () {
    Route::prefix('backend')->group(function () {
        Route::resource('pendidikan', 'Backend\PendidikanController', ['names' => [
            'index' => 'pendidikan'
        ]]);
        // Route::resource('pendidikan', 'PendidikanController');
        Route::resource('pengalaman', 'Backend\PengalamanKerjaController', ['names' => [
            'index' => 'pengalaman'
        ]]);

    });

});
require __DIR__.'/auth.php';
