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
                  {{ isset($title) ? 'Mengubah' : 'Menambah' }}  Pengalaman Kerja
                </header>
                <!-- form input -->
                <div class="panel-body">
                    <div class="form">
                        <form action=" {{ isset($data) ? route('pengalaman.update', $data->id) : route('pengalaman.store') }} " class="form-validate form-horizontal" id="pengalaman_kerja_form" method="POST">
                          @csrf
                          {!! isset($data) ? method_field('PUT') : '' !!}
                          <div class="form-group">
                            <label for="cname" class="control-label col-lg-2">Nama Perusahaan<span class="required">*</span></label>
                            <div class="col-lg-10">
                              <input type="text" class="form-control" value=" {{ isset($data) ? $data->nama : '' }} " id="nama" name="nama" minlength="5" required>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="cname" class="control-label col-lg-2">Jabatan<span class="required">*</span></label>
                            <div class="col-lg-10">
                              <input type="text" class="form-control" value="{{ isset($data) ? $data->jabatan : '' }} " id="jabatan" name="jabatan" minlength="2" required>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="curl" class="control-label col-lg-2">Tahun Masuk<span class="required">*</span></label>
                            <div class="col-lg-10">
                              <input type="text" class="form-control" value=" {{ isset($data) ? $data->tahun_masuk : '' }} " id="tahun_masuk" name="tahun_masuk" minlength="4" required>
                            </div>
                          </div>
                          <div class="form-group">
                            <label for="curl" class="control-label col-lg-2">Tahun Selesai<span class="required">*</span></label>
                            <div class="col-lg-10">
                              <input type="text" class="form-control" value=" {{ isset($data) ? $data->tahun_keluar : '' }} " id="tahun_keluar" name="tahun_keluar" minlength="4" required>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                              <button class="btn btn-primary" type="submit">Simpan</button>
                              <a href=" {{ route('pengalaman') }} ">
                                <button class="btn btn-default" type="button">Kembali</button>
                              </a>
                            </div>
                          </div>
                        </form>
                    </div>
                </div>
            </section>
          </div>
      </div>
      <!-- page end-->
    </section>
</section>
<!--main content end-->
