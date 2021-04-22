<?php

namespace Database\Seeders;

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
        DB::table('detail_profile')->insert([
            'address' => 'jember',
            'nomor_tlp' => '082889755587',
            'ttl' => '2000-11-26',
            'foto' => 'picture.png',
        ]);
    }
}
