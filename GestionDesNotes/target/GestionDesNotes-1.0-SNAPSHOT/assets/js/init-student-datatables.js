
$(document).ready(function () {
  'use strict';

  var dt_basic_table = $('#student-table');

  //console.log(window.jsonData);

  // DataTable with buttons
  // --------------------------------------------------------------------

  if (dt_basic_table.length) {
    var dt_basic = dt_basic_table.DataTable({
      data: data.data,
      columns: [
        { data: '' },
        { data: 'id' },
        { data: 'nom' },
        { data: 'prenom' },
        { data: 'tp' },
        { data: 'cc' },
        { data: 'projet' },
        { data: 'presentation' },
        { data: 'note_total' },
        { data: 'valide' },
        { data: '' }
      ],
      columnDefs: [
        {
          // For Responsive
          className: 'control',
          orderable: false,
          responsivePriority: 2,
          searchable: false,
          targets: 0,
          render: function (data, type, full, meta) {
            return '';
          }
        },
        {
          targets: 1,
          orderable: false,
          responsivePriority: 3,
          checkboxes: {
            selectAllRender: '<input type="checkbox" class="form-check-input">'
          },
          render: function () {
            return '<input type="checkbox" class="dt-checkboxes form-check-input">';
          }
        },
        {
          // Label
          targets: -2,
          render: function (data, type, full, meta) {
            var $status_number = full['valide'];
            var $status = {
              1: { title: 'Validé', class: 'bg-label-success' },
              0: { title: 'Non validé', class: ' bg-label-danger' },
            };
            if (typeof $status[$status_number] === 'undefined') {
              return data;
            }
            return (
              '<span class="badge rounded-pill ' +
              $status[$status_number].class +
              '">' +
              $status[$status_number].title +
              '</span>'
            );
          }
        },
        {
          // Actions
          targets: -1,
          title: 'Actions',
          orderable: false,
          searchable: false,
          render: function (data, type, full, meta) {
            return (
              '<div class="d-inline-block">' +
              '<a href="javascript:;" class="btn btn-sm text-primary btn-icon dropdown-toggle hide-arrow" data-bs-toggle="dropdown"><i class="bx bx-dots-vertical-rounded"></i></a>' +
              '<ul class="dropdown-menu dropdown-menu-end">' +
              '<li><a href="javascript:;" class="dropdown-item">Détails</a></li>' +
              '<div class="dropdown-divider"></div>' +
              '<li><a href="javascript:;" class="dropdown-item text-danger delete-record">Supprimer</a></li>' +
              '</ul>' +
              '</div>' +
              '<a href="javascript:;" class="btn btn-sm text-primary btn-icon item-edit"><i class="bx bxs-edit"></i></a>'
            );
          }
        }
        // Your other column definitions here...
      ],
      initComplete: function () {

        // Add event listener for edit button
        dt_basic_table.on('click', '.item-edit', function () {
          editItem(this);
        });

        // Add event listener for save button
        dt_basic_table.on('click', '.save-row', function () {
          saveRow(this);
        });

        // Add event listener for remove button
        dt_basic_table.on('click', '.delete-record', function () {
          var confirmed = confirm("Voullez-vous vraiment supprimer cet étudiant ?");

          if (confirmed) {
            var row = $(this).closest('tr');
            dt_basic.row(row).remove().draw();
          }
        });
      },
      select: {
        style: 'multi',
        selector: 'td:first-child'
      },
      order: [[2, 'desc']],
      dom: '<"card-header"<"head-label text-center"><"dt-action-buttons text-end"B>><"d-flex justify-content-between align-items-center row"<"col-sm-12 col-md-6"l><"col-sm-12 col-md-6"f>>t<"d-flex justify-content-between row"<"col-sm-12 col-md-6"i><"col-sm-12 col-md-6"p>>',
      displayLength: 7,
      lengthMenu: [7, 10, 25, 50, 75, 100],
      buttons: [
        {
          extend: 'collection',
          className: 'btn btn-label-primary btn-outline-primary dropdown-toggle me-2',
          text: '<i class="bx bx-show me-1"></i>Exporter',
          buttons: [
            {
              extend: 'print',
              text: '<i class="bx bx-printer me-1" ></i>Imprimmer',
              className: 'dropdown-item',
              exportOptions: { columns: [3, 4, 5, 6, 7] }
            },
            {
              extend: 'csv',
              text: '<i class="bx bx-file me-1" ></i>Csv',
              className: 'dropdown-item',
              exportOptions: { columns: [3, 4, 5, 6, 7] }
            },
            {
              extend: 'excel',
              text: 'Excel',
              className: 'dropdown-item',
              exportOptions: { columns: [3, 4, 5, 6, 7] }
            },
            {
              extend: 'pdf',
              text: '<i class="bx bxs-file-pdf me-1"></i>Pdf',
              className: 'dropdown-item',
              exportOptions: { columns: [3, 4, 5, 6, 7] }
            },
            {
              extend: 'copy',
              text: '<i class="bx bx-copy me-1" ></i>Copier',
              className: 'dropdown-item',
              exportOptions: { columns: [3, 4, 5, 6, 7] }
            }
          ]
        },
        {
          text: '<i class="bx bx-plus me-1"></i> <span class="d-none d-lg-inline-block">Ajouter un nouveau étudiant</span>',
          className: 'create-new btn btn-primary',
          action: function () {
            // Call the function to add a new row
            addNewRow();
          }
        },
        {
          text: '<i class="bx bx-check-circle me-1"></i> <span class="d-none d-lg-inline-block">Valider l\'élément</span>',
          className: 'validate-element btn btn-success',
          action: function () {
            // Call the function to add a new row
            ValidateElement();
            postNotes();
          }
        },
        {
          text: '<i class="bx bx-check-circle me-1"></i> <span class="d-none d-lg-inline-block">Enregister</span>',
          className: 'post-notes btn btn-warning',
          action: function () {
            // Call the function to add a new row
            postNotes();
          }
        }
      ],
      responsive: {
        details: {
          display: $.fn.dataTable.Responsive.display.modal({
            header: function (row) {
              var data = row.data();
              return 'Details of ' + data['full_name'];
            }
          }),
          type: 'column',
          renderer: function (api, rowIdx, columns) {
            var data = $.map(columns, function (col, i) {
              return col.title !== '' // ? Do not show row in modal popup if title is blank (for check box)
                ? '<tr data-dt-row="' +
                col.rowIndex +
                '" data-dt-column="' +
                col.columnIndex +
                '">' +
                '<td>' +
                col.title +
                ':' +
                '</td> ' +
                '<td>' +
                col.data +
                '</td>' +
                '</tr>'
                : '';
            }).join('');

            return data ? $('<table class="table"/><tbody />').append(data) : false;
          }
        }
      },
      "language": {
        "sEmptyTable": "Aucune donnée disponible dans le tableau",
        "sInfo": "Affichage de l'élément _START_ à _END_ sur _TOTAL_ éléments",
        "sInfoEmpty": "Affichage de l'élément 0 à 0 sur 0 élément",
        "sInfoFiltered": "(filtré à partir de _MAX_ éléments au total)",
        "sInfoPostFix": "",
        "sInfoThousands": ",",
        "sLengthMenu": "Afficher _MENU_ éléments",
        "sLoadingRecords": "Chargement...",
        "sProcessing": "Traitement...",
        "sSearch": "Rechercher :",
        "sZeroRecords": "Aucun élément correspondant trouvé",
        "oPaginate": {
          "sFirst": "Premier",
          "sLast": "Dernier",
          "sNext": "Suivant",
          "sPrevious": "Précédent"
        },
        "oAria": {
          "sSortAscending": ": activer pour trier la colonne par ordre croissant",
          "sSortDescending": ": activer pour trier la colonne par ordre décroissant"
        }
      },
    });
    $('div.head-label').html('<h5 class="card-title mb-0">Liste des étudiants</h5>');
  }

  function addNewRow() {
    var newRowData = {
      "id": '',
      "nom": '',
      "prenom": '',
      "tp": '',
      "cc": '',
      "projet": '',
      "presentation": '',
      "note_total": '',
      "valide": '',
    };

    var newRow = dt_basic.row.add(newRowData).draw(false);
    var index = newRow.index();
    var newRowNode = newRow.node();
    var keys = Object.keys(newRowData);

    // Enable inline editing for the new row
    dt_basic.cells(index, [2, 3, 4, 5, 6, 7]).nodes().to$().each(function (cellIndex) {
      // Get the key for the nom column index
      var key = keys[cellIndex + 1];
      var editable = '';
      if (['cc', 'tp', 'projet', 'presentation'].includes(key)) {
        editable = 'editable';
      }
      $(this).html('<input type="text" name="' + key + '" class="form-control ' + editable + '" value="' + $(this).text() + '">');
    });

    $(newRowNode).find('.d-inline-block').append('<a title="Valider" href="javascript:;" class="btn btn-sm text-success btn-icon save-row"><i class="bx bx-check-circle"></i></a>');
    $(newRowNode).find('.d-inline-block').append('<a title="Supprimer" href="javascript:;" class="btn btn-sm text-danger btn-icon delete-record"><i class="bx bxs-trash"></i></a>');
    $(newRowNode).find('.item-edit').hide();

    // Add event listener for editing
    // dt_basic.cells(index, [2, 3, 4, 5, 6, 7, 8, 9]).nodes().to$().find('input').on('blur', function () {
    //   var cellIndex = dt_basic.cell($(this).closest('td')).index();
    //   var newData = $(this).val();
    //   dt_basic.cell(cellIndex.row, cellIndex.column).data(newData);
    // });
  }

  function saveRow(_this) {
    var row = $(_this).closest('tr');
    var inputs = row.find('input.editable');
    var rowData = dt_basic.row(row).data();
    var rowIndex = dt_basic.row(row).index();
    var noteTotal = 0;
    var newData = {};

    var allValuesEmpty = Object.values(rowData).every(function (value) {
      return value === "";
    });

    // if new row is add
    if (allValuesEmpty) {
      $(row).find('td input[type="text"]').each(function () {
        var inputValue = $(this).val();
        var columnName = $(this).attr('name');
        rowData[columnName] = inputValue;
      });
    }
    newData = { ...rowData };

    // Collect new data from input fields for specific keys
    inputs.each(function (i) {
      var columnIndex = i + 3;
      var key = Object.keys(rowData)[columnIndex]; // Starting from index 3 => tp 

      if (['cc', 'tp', 'projet', 'presentation'].includes(key)) { // Specify the keys you want to edit
        newData[key] = $(this).val();
        noteTotal += parseFloat($(this).val());
      }
    });

    // newData['note_total'] = parseFloat(noteTotal / 4).toFixed(2);
    // Update DataTable cell data

    dt_basic.row(row).data(newData).invalidate().draw();
    // dt_basic.row(row).data(newData).remove();
    // var newRow = dt_basic.row.add(newData).draw();

    // Show the Edit button and remove the Save button
    row.find('.item-edit').show();
    row.find('.save-row').remove();
  }

  function editItem(_this) {
    var row = $(_this).closest('tr');
    var rowData = dt_basic.row(row).data();

    // Replace the content of each cell with input fields for specific keys
    row.find('td').each(function (i, td) {
      var key = Object.keys(rowData)[i - 1];
      if (['tp', 'cc', 'projet', 'presentation'].includes(key)) { // Specify the keys you want to edit
        $(td).html('<input type="text" class="form-control editable" value="' + rowData[key] + '">');
      }
    });

    // Add Save button to save changes
    row.find('.d-inline-block').append('<a class="btn btn-sm btn-success text-white save-row">Save</a>');
    // Hide the Edit button
    row.find('.item-edit').hide();
  }

  function ValidateElement() {
    // Disable click actions for the entire DataTable
    bootbox.confirm({
      message: "Voulez-vous vraiment valider cet élement ?",
      buttons: {
        confirm: {
          label: 'Oui',
          className: 'btn-success'
        },
        cancel: {
          label: 'Non',
          className: 'btn-danger'
        }
      },
      callback: function (result) {
        if (result) {
          dt_basic.off('click');
          dt_basic.buttons().disable();

          // Change the color to gray for the entire DataTable
          dt_basic.rows().nodes().to$().css('background-color', '#f2f2f2'); // Set the desired background color
          dt_basic.cells(':checkbox').nodes().to$().prop('disabled', true);
          dt_basic.cells().nodes().to$().find('a.btn').addClass('disabled');
          toastr.success('L\'élement a été valider avec succès !');
        }
      }
    });
  }

  function postNotes() {
    bootbox.confirm({
      message: "Voulez-vous vraiment enregister cet élement ?",
      buttons: {
        confirm: {
          label: 'Oui',
          className: 'btn-success'
        },
        cancel: {
          label: 'Non',
          className: 'btn-danger'
        }
      },
      callback: function (result) {
        if (result) {
          var tableData = dt_basic.rows().data().toArray();
          var codeElement = getUrlParameter('code_element');

          $.each(tableData, function(index, element) {
            element.code_element = codeElement;
          });
          var jsonData = JSON.stringify(tableData);
          console.log(jsonData);
          console.log(codeElement);

          $.ajax({
            url: 'enregistrerNotes',
            method: 'POST',
            data: jsonData,
            contentType: 'application/json',
            success: function (response) {
              toastr.success('L\'élement a été enregister avec succès !');
            },
            error: function (error) {
              // Handle the error
            }
          });
        }
      }
    });
  }

  function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
      sParameterName = sURLVariables[i].split('=');

      if (sParameterName[0] === sParam) {
        return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
      }
    }
    return false;
  }
});

