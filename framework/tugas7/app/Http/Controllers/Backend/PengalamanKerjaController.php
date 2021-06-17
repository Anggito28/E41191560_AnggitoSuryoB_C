<?php

namespace App\Http\Controllers\Backend;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\PengalamanKerja;

class PengalamanKerjaController extends Controller
{

    public function __construct() {
        $this->param['title'] = "Riwayat Kerja";
    }
    public function index()
    {
        $this->param['data'] = PengalamanKerja::all();
        return view('backend.pengalaman.index', $this->param);
    }


    public function create()
    {
        $this->param['title'] = 'Menambah';
        return view('backend.pengalaman.create');
    }

    public function store(Request $request)
    {
        $validate = $request->validate([
            'nama' => 'required|max:100',
            'jabatan' =>'required',
            'tahun_masuk' => 'required',
            'tahun_keluar' => 'required'
        ],
        [
            'required' => ':Data Harus Terisi',
        ]
        );
        $pengalaman = new PengalamanKerja;
        $pengalaman->nama = $request->nama;
        $pengalaman->jabatan = $request->jabatan;
        $pengalaman->tahun_masuk = $request->tahun_masuk;
        $pengalaman->tahun_keluar = $request->tahun_keluar;
        $pengalaman->save();
        return redirect()->route('pengalaman')->with('success','Data Pengalaman kerja baru telah berhasil disimpan');
    }

    public function show($id)
    {
        //
    }


    public function edit($id)
    {
        $this->param['title'] = 'Mengubah';
        $this->param['data'] = PengalamanKerja::findOrFail($id);
        return view('backend.pengalaman.create', $this->param);
    }

    public function update(Request $request, $id)
    {
        $updatePengalaman = PengalamanKerja::findOrFail($id);
        $updatePengalaman->nama = $request->nama;
        $updatePengalaman->jabatan = $request->jabatan;
        $updatePengalaman->tahun_masuk = $request->tahun_masuk;
        $updatePengalaman->tahun_keluar = $request->tahun_keluar;
        $updatePengalaman->save();
        return redirect()->route('pengalaman')->with('success', 'Pengalaman Kerja Berhasil Diperbarui');
    }

    public function destroy($id)
    {
        $deletePengalaman = PengalamanKerja::findOrFail($id);
        $deletePengalaman->delete();
        return redirect()->route('pengalaman')->with('success','Data Pengalaman Kerja berhasil dihapus');
    }
}
