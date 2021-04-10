<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class DetailProfileSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        //
        DB::table('detail_profile')->insert([
            'address' => 'Jember',
            'nomor_tlp' => '0811544482',
            'ttl' => '2000-11-26',
            'foto' => 'picture.png'
        ]);
    }
}
