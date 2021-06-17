@include('layouts.template')
<!--main content start-->
<section id="main-content">
    <section class="wrapper">
      <div class="row">
        <div class="col-lg-12">
          <h3 class="page-header"><i class="icon_document_alt"></i>Riwayat Hidup</h3>
          <ol class="breadcrumb">
            <li><i class="fa fa-home"></i><a href=" {{ url('/') }} ">Dashboard</a></li>
            <li><i class="icon_document_alt"></i>Riwayat Hidup</li>
            <li><i class="fa fa-files-o"></i>Pengalaman Kerja</li>
          </ol>
        </div>
      </div>
      <!-- page start-->
      <div class="row">
          <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    Pengalaman Kerja
                </header>
                <div class="panel-body">
                    @if ($message = Session::get('success'))
                        <div class="alert alert-success">
                            <p> {{ $message }} </p>
                        </div>
                    @endif
                    <a href=" {{ route('pengalaman.create') }} ">
                        <button class="btn btn-primary"><i class="fa fa-plus"></i>Tambah Data</button>
                    </a>

                    <div style="margin-top: 20px">
                        <table class="table table-striped table-advance table-hover">
                            <tbody>
                              <tr>
                                <th>Nama Perusahaan</th>
                                <th>Jabatan</th>
                                <th>Tahun Masuk</th>
                                <th> Tahun Keluar</th>
                                <th><i class="icon_cogs"></i> Action</th>
                              </tr>
                              @foreach ($data as $item)
                                <tr>
                                    <td> {{ $item->nama }} </td>
                                    <td> {{ $item->jabatan }} </td>
                                    <td> {{ $item->tahun_masuk }} </td>
                                    <td> {{ $item->tahun_keluar }} </td>
                                    <td>
                                    <div class="btn-group">
                                        <form action=" {{ route('pengalaman.destroy', $item->id) }} " method="POST">
                                            <a class="btn btn-warning" href=" {{ route('pengalaman.edit',$item->id) }} "><i class="icon_check_alt2"></i></a>
                                            @csrf
                                            @method('DELETE')
                                            <button type="submit" class="btn btn-danger" onclick="return confirm('Apakah Anda Yakin ingin menghapus data?')"><i class="fa fa-trash-o"></i></button>
                                        {{-- <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a> --}}
                                        </form>
                                    </div>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                          </table>
                    </div>
                </div>
            </section>
          </div>
      </div>
      <!-- page end-->
    </section>
</section>
<!--main content end-->
