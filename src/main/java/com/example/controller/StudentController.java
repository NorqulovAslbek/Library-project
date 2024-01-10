package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * Bu method student malumotlarini database ga
     * create qilish uchun ishlatiladiagan method.
     *
     * @param dto StudentDTO typedagi parametr qabul qiladi @RequestBody anatatsyasi bizga
     *            json yoki xml formatdagi malumotni StudentDTO ga ozgartrib beradi.
     * @return -> ResponseEntity <StudentDTO>
     */

    @PostMapping("")
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.create(dto)); // 200
    }

    /**
     * Barcha studentlar haqidagi nalumotlarni database dan olish uchun ishlatiladigan method
     *
     * @return ResponseEntity
     */

    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> all() {
        return ResponseEntity.ok(studentService.getAll());
    }

    /**
     * id si bo'yicha Studentni olish uchun ishlatiladigan method
     *
     * @param id Integer
     * @return ResponseEntity
     */

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    /**
     * id orqali studentni delete qilish uchun ishlatilgan method.
     *
     * @param id Integer
     * @return ResponseEntity <Boolean>
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.deleteStudentById(id));
    }

    /**
     * id orqali update qiluvchi method.
     * @param id Integer
     * @param studentDTO StudentDTO
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudentById(id, studentDTO));
    }


}
